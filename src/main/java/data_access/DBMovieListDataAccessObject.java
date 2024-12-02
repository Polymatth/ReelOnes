package data_access;

import entity.*;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.add_movie_to_list.AddMovieDataAccessInterface;
import use_case.edit_list.EditListDataAccessInterface;
import use_case.movie_detail.MovieDetailDataAccessInterface;
import use_case.movie_list.MovieListDataAccessInterface;
import use_case.open_list.OpenListDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static data_access.DBUserDataAccessObject.*;
import static java.lang.Integer.parseInt;

public class DBMovieListDataAccessObject implements MovieListDataAccessInterface, EditListDataAccessInterface,
        OpenListDataAccessInterface, AddMovieDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String MESSAGE = "message";
    private static final String FAVMOVIE = "favoriteMovie";
    private static final String FAVDIRECTOR = "favoriteDirector";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MOVIELISTS = "movieLists";

    private final OkHttpClient client;

    public DBMovieListDataAccessObject() {
        this.client = new OkHttpClient().newBuilder().build();
    }

    @Override
    public List<MovieList> getUserListsForUser(String username) {
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/movieLists?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                JSONArray listsArray = responseBody.getJSONArray("lists");
                List<MovieList> movieLists = new ArrayList<>();

                for (int i = 0; i < listsArray.length(); i++) {
                    JSONObject listObject = listsArray.getJSONObject(i);
                    String name = listObject.getString("name");
                    JSONArray moviesArray = listObject.getJSONArray("movies");

                    List<Movie> movies = parseMovies(moviesArray);
                    movieLists.add(new UserList(username,name, false, movies));
                }

                return movieLists;
            } else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error fetching movie lists", ex);
        }
    }

    @Override
    public void saveMovieList(User user) {
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());

        // Add "info" object with favorite movie and favorite director
        JSONObject infoObject = new JSONObject();
        infoObject.put(FAVMOVIE, user.getFavMovie());
        infoObject.put(FAVDIRECTOR, user.getFavDirector());
        infoObject.put(MOVIELISTS, parseMovieListsToJSONArray(user.getMovieLists()));
        requestBody.put("info", infoObject);
        System.out.println("saved movie list");


        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) != SUCCESS_CODE) {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error saving movie list", ex);
        }
    }

    public void deleteMovieList(String username, String listName) {
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/movieList?username=%s&listName=%s", username, listName))
                .method("DELETE", null)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) != SUCCESS_CODE) {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error deleting movie list", ex);
        }
    }

    public List<Movie> parseMovies(JSONArray moviesArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < moviesArray.length(); i++) {
            JSONObject movieObject = moviesArray.getJSONObject(i);
            String poster_path = movieObject.getString("poster_path");
            boolean adult = movieObject.getBoolean("adult");
            String overview = movieObject.getString("overview");
            String release_date = movieObject.getString("release_date");

            JSONArray genreJsonArray = movieObject.getJSONArray("genre_ids");
            List genre_ids = new ArrayList();
            int size = genreJsonArray.length();
            int j = 0;
            while (j< size){
                genre_ids.add(genreJsonArray.get(j));
                j++;
            }

            int id = movieObject.getInt("id");
            String original_language = movieObject.getString("original_language");
            String title = movieObject.getString("title");
            String backdrop_path = movieObject.getString("backdrop_path");
            float popularity = movieObject.getFloat("popularity");
            int vote_count = movieObject.getInt("vote_count");
            boolean video = movieObject.getBoolean("video");
            float vote_average = movieObject.getFloat("vote_average");
            movies.add(new Movie(poster_path, adult, overview, release_date,
                    genre_ids, id, original_language, title, backdrop_path,
                    popularity, vote_count, video, vote_average));
        }
        return movies;
    }

    public List<MovieList> parseMovieLists(String movieListArrayString) throws JSONException {
        // Convert the input string back to a JSONArray
        JSONArray movieListArray = new JSONArray(movieListArrayString);

        List<MovieList> movieLists = new ArrayList<>();
        for (int i = 0; i < movieListArray.length(); i++) {
            // Get each sub-array representing a MovieList
            JSONArray subJSONMovieList = movieListArray.getJSONArray(i);

            // Extract fields in the same order they were added
            String userId = subJSONMovieList.getString(0);
            String listName = subJSONMovieList.getString(1);
            boolean isPublic = subJSONMovieList.getBoolean(2);

            // Convert the movies JSONArray back to a List<Movie>
            JSONArray moviesArray = subJSONMovieList.getJSONArray(3);
            List<Movie> movies = parseMovies(moviesArray);

            // Add to the resulting list
            movieLists.add(new UserList(userId, listName, isPublic, movies));
        }
        return movieLists;
    }

    public String parseMovieListsToJSONArray(List<MovieList> movieLists) {
        JSONArray JSONMovieLists = new JSONArray();
        for (MovieList movieList : movieLists) {
            JSONArray subJSONMovieList = new JSONArray();
            subJSONMovieList.put(movieList.getUserId());
            subJSONMovieList.put(movieList.getListName());
            subJSONMovieList.put(movieList.getPublic());
            subJSONMovieList.put(movieList.getMovies());
            JSONMovieLists.put(subJSONMovieList);
        }
        return JSONMovieLists.toString();
    }

    private JSONArray serializeMovies(List<Movie> movies) {
        JSONArray moviesArray = new JSONArray();
        for (Movie movie : movies) {
            JSONObject movieObject = new JSONObject();
            movieObject.put("title", movie.getTitle());
            movieObject.put("director", movie.getDirector());
            movieObject.put("year", movie.getYear());
            moviesArray.put(movieObject);
        }
        return moviesArray;
    }

    @Override
    public MovieList getMovieListByName(String listName) {
        return null;
    }

    @Override
    public MovieList getList(String username, String listName) {
        List<MovieList> userLists = this.getUserListsForUser(username);
        for (MovieList list : userLists) {
            if (listName.equals(list.getListName())) {
                return list;
            } else {
                throw new RuntimeException("List does not exist.");
            }
        }
        return null;
    }

    @Override
    public void updateList(String username, MovieList movieList) {
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);

        // Create the JSON request body
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, username);
        requestBody.put("listName", movieList.getListName());
        requestBody.put("isPublic", movieList.getPublic());
        requestBody.put("movies", serializeMovies(movieList.getMovies()));

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);

        // Build the PUT request
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/updateMovieList")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try {
            // Execute the request and handle the response
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) != SUCCESS_CODE) {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error updating movie list", ex);
        }
    }

    public void updateList(User user) {
        this.saveMovieList(user);
    }

    @Override
    public List<Movie> getMoviesForList(String listName) {
        return List.of();
    }

    @Override
    public void addMovieToList(String listId, String movieId) {
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);

        // Create the JSON request body
        final JSONObject requestBody = new JSONObject();
        requestBody.put("listId", listId);
        requestBody.put("movieId", movieId);

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);

        // Build the POST request
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/addMovieToList")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try {
            // Execute the request and handle the response
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) != SUCCESS_CODE) {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error adding movie to list", ex);
        }
    }
}