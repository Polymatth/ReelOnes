package data_access;

import entity.Movie;
import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesDataAccessInterface;
import use_case.fetch_popularmovies.FetchPopularMoviesDataAccessInterface;
import use_case.movie_detail.MovieDetailDataAccessInterface;
import use_case.search_movie.SearchMovieDataAccessInterface;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.*;

/**
 * Implementation of SearchMovieDataAccessInterface to fetch movie data from API
 */
public class MovieAPIAccess implements SearchMovieDataAccessInterface, MovieDetailDataAccessInterface, FetchNowPlayingMoviesDataAccessInterface , FetchPopularMoviesDataAccessInterface {

    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private final OkHttpClient client;
    private final String apiKey;
    private final String apiUrl;

    public MovieAPIAccess(String apiKey, String apiUrl) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        this.client = new OkHttpClient();
    }

    @Override
    public List<Movie> searchMoviesByQuery(String query) {
        List<Movie> movies = new ArrayList<>();
        try {

            String requestUrl = apiUrl + "?query=" + query + "&api_key=" + apiKey;
            Request request = new Request.Builder()
                    .url(requestUrl)
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                System.out.println(jsonResponse);
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray results = jsonObject.getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    JSONObject movieJson = results.getJSONObject(i);

                    JSONArray genreJsonArray = movieJson.getJSONArray("genre_ids");
                    List genre_ids = new ArrayList();
                    int size = genreJsonArray.length();
                    int j = 0;
                    while (j< size){
                        genre_ids.add(genreJsonArray.get(j));
                        j++;
                    }

                    Movie movie = new Movie(
                            movieJson.optString("poster_path"),
                            movieJson.getBoolean("adult"),
                            movieJson.getString("overview"),
                            movieJson.getString("release_date"),
                            genre_ids,
                            movieJson.getInt("id"),
                            movieJson.getString("original_language"),
                            movieJson.getString("title"),
                            movieJson.optString("backdrop_path"),
                            movieJson.getFloat("popularity"),
                            movieJson.getInt("vote_count"),
                            movieJson.getBoolean("video"),
                            movieJson.getFloat("vote_average")
                    );
                    movies.add(movie);
                }
            } else {
                System.out.println("API request failed with code: " + response.code());
            }
        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> searchMoviesByGenre(List<Integer> genreList) {
        List<Movie> movies = new ArrayList<>();
        try {
            StringBuilder requestUrl = new StringBuilder("https://api.themoviedb.org/3/discover/movie?api_key=" +
                    apiKey + "&with_genres=");
            for (Integer id: genreList) {
                requestUrl.append(id).append(",");
            }
            if (requestUrl.substring(requestUrl.length() - 1) == ","){
                requestUrl.deleteCharAt(requestUrl.length() - 1);
            }
            Request request = new Request.Builder()
                    .url(requestUrl.toString())
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                System.out.println(jsonResponse);
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray results = jsonObject.getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    JSONObject movieJson = results.getJSONObject(i);

                    JSONArray genreJsonArray = movieJson.getJSONArray("genre_ids");
                    List genre_ids = new ArrayList();
                    int size = genreJsonArray.length();
                    int j = 0;
                    while (j< size){
                        genre_ids.add(genreJsonArray.get(j));
                        j++;
                    }

                    Movie movie = new Movie(
                            movieJson.optString("poster_path"),
                            movieJson.getBoolean("adult"),
                            movieJson.getString("overview"),
                            movieJson.getString("release_date"),
                            genre_ids,
                            movieJson.getInt("id"),
                            movieJson.getString("original_language"),
                            movieJson.getString("title"),
                            movieJson.optString("backdrop_path"),
                            movieJson.getFloat("popularity"),
                            movieJson.getInt("vote_count"),
                            movieJson.getBoolean("video"),
                            movieJson.getFloat("vote_average")
                    );
                    movies.add(movie);
                }
            } else {
                System.out.println("API request failed with code: " + response.code());
            }
        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }

    /**
     * Returns the name of the genre that corresponds to each genre ID in the API.
     * @return the genre that corresponds to each genre ID.
     */
    public Map<Integer, String> getAllGenres() {
        Map<Integer, String> genres = new HashMap<>();
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/genre/movie/list?language=en" + "&api_key=" + this.apiKey)
                    .get()
                    .addHeader("accept", CONTENT_TYPE_JSON)
                    .addHeader("Authorization", "Bearer application/json")
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray results = jsonObject.getJSONArray("genres");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject elem = results.getJSONObject(i);
                    Integer id = elem.getInt("id");
                    String name = elem.getString("name");
                    genres.put(id, name);
                }
            }
            else{
                    System.out.println("API request failed with code: " + response.code());
                }
        }catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
        }
        return genres;
    }

    /**
     * Returns the list of genre names that correspond to a list of genre IDs for a given movie
     * @param genreIDs the list of genre IDs for a specific movie
     * @return the list of the genre names for that movie.
     */
    public List<String> getGenres(List<Integer> genreIDs) {
        Map<Integer, String> allGenres = getAllGenres();
        List<String> genreList = new ArrayList<>();
        for (int genreId : genreIDs) {
            genreList.add(allGenres.get(genreId));
        }
        return genreList;
    }

    /**
     * Returns the director(s) of a given movie, or unknown if not found.
     * @param movieID The ID of the movie whose director we're looking for
     * @return the name(s) of the director(s), or unknown if not found.
     */
    public String getDirector(int movieID) {
        try {
            String url = "https://api.themoviedb.org/3/movie/" + movieID + "/credits?language=en-US" + "&api_key=" + apiKey;
            String authToken = "Bearer 688717ab16b692bc28e554309061593f";
            //System.out.println("Url: " + url + " Auth Token: " + authToken);
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("accept", CONTENT_TYPE_JSON)
                    .addHeader("Authorization", authToken)
                    .build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray results = jsonObject.getJSONArray("crew");
                List<String> directors = new ArrayList<>();
                for (int i = 0; i < results.length(); i++) {
                    JSONObject elem = results.getJSONObject(i);
                    if (elem.getString("job").equals("Director")) {
                        directors.add(elem.getString("name"));
                    }
                }
                return String.join(", ", directors);
            }
            else {
                System.out.println("API request failed with code: " + response.code());
            }
        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

    /**
     * Returns the list of streaming services on which a given movie is available
     * @param movieID the ID of the movie we want to find streaming services playing
     * @return the list of the names of the streaming services that have that movie.
     */
    public List<String> getStreamingServices(int movieID) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/movie/" + movieID + "/watch/providers" + "?api_key=" + this.apiKey)
                    .get()
                    .addHeader("accept", CONTENT_TYPE_JSON)
                    .addHeader("Authorization", "Bearer application/json")
                    .build();


            List<String> providerList = new ArrayList<>();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String locale = Locale.getDefault().getCountry();
                String jsonResponse = response.body().string();
                System.out.println(jsonResponse);
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONObject countriesToOptions = jsonObject.getJSONObject("results");
                if (countriesToOptions != null && !countriesToOptions.isEmpty() && countriesToOptions.keySet()
                        .contains(locale)) {
                    JSONObject localeOptions = ((JSONObject)countriesToOptions.get(locale));
                    if (localeOptions.keySet().contains("flatrate") && localeOptions.get("flatrate") != null) {
                        for (int i = 0; i < ((JSONArray)(localeOptions.get("flatrate"))).length(); i++) {
                            String provider = (String) ((JSONObject)((JSONArray)localeOptions.get("flatrate"))
                                    .get(i)).get("provider_name");
                            System.out.println(provider);
                            providerList.add(provider);
                        }
                    }
                }
                return providerList;
            }
            else {
                System.out.println("API request failed with code: " + response.code());
            }
        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
        }
        List<String> failList = new ArrayList<>();
        return failList;
    }

    public List<Movie> getNowPlayingMovies(){
        List<Movie> movies = new ArrayList<>();
        try {
            String requestUrl = apiUrl + apiKey +"&language=en-US&page=1 ";

            Request request = new Request.Builder()
                    .url(requestUrl)
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                    .build();

            // Execute the API call
            Response response = client.newCall(request).execute();

            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray results = jsonObject.getJSONArray("results");

                // Parse each movie from the response
                for (int i = 0; i < results.length(); i++) {
                    JSONObject movieJson = results.getJSONObject(i);

                    JSONArray genreJsonArray = movieJson.optJSONArray("genre_ids");
                    List<Integer> genreIds = new ArrayList<>();
                    if (genreJsonArray != null) {
                        for (int j = 0; j < genreJsonArray.length(); j++) {
                            genreIds.add(genreJsonArray.getInt(j));
                        }
                    }

                    // Create a Movie object
                    Movie movie = new Movie(
                            movieJson.optString("poster_path"),
                            movieJson.optBoolean("adult", false),
                            movieJson.optString("overview"),
                            movieJson.optString("release_date"),
                            genreIds,
                            movieJson.optInt("id"),
                            movieJson.optString("original_language"),
                            movieJson.optString("title"),
                            movieJson.optString("backdrop_path"),
                            movieJson.optFloat("popularity", 0),
                            movieJson.optInt("vote_count", 0),
                            movieJson.optBoolean("video", false),
                            movieJson.optFloat("vote_average", 0)
                    );
                    movies.add(movie);
                }
            } else {
                System.out.println("API request failed with code: " + response.code());
            }
        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> getUpcomingMovies() {
        List<Movie> movies = new ArrayList<>();
        try {
            String requestUrl = apiUrl + "/movie/upcoming?language=en-US&page=1&api_key=" + apiKey;

            Request request = new Request.Builder()
                    .url(requestUrl)
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                    .build();

            // Execute the API call
            Response response = client.newCall(request).execute();

            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray results = jsonObject.getJSONArray("results");

                // Parse each movie from the response
                for (int i = 0; i < results.length(); i++) {
                    JSONObject movieJson = results.getJSONObject(i);

                    JSONArray genreJsonArray = movieJson.optJSONArray("genre_ids");
                    List<Integer> genreIds = new ArrayList<>();
                    if (genreJsonArray != null) {
                        for (int j = 0; j < genreJsonArray.length(); j++) {
                            genreIds.add(genreJsonArray.getInt(j));
                        }
                    }

                    // Create a Movie object
                    Movie movie = new Movie(
                            movieJson.optString("poster_path"),
                            movieJson.optBoolean("adult", false),
                            movieJson.optString("overview"),
                            movieJson.optString("release_date"),
                            genreIds,
                            movieJson.optInt("id"),
                            movieJson.optString("original_language"),
                            movieJson.optString("title"),
                            movieJson.optString("backdrop_path"),
                            movieJson.optFloat("popularity", 0),
                            movieJson.optInt("vote_count", 0),
                            movieJson.optBoolean("video", false),
                            movieJson.optFloat("vote_average", 0)
                    );
                    movies.add(movie);
                }
            } else {
                System.out.println("API request failed with code: " + response.code());
            }
        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
        }
        return movies;

    }

    public List<Movie> getPopularMovies(){
        List<Movie> movies = new ArrayList<>();
        try {
            // Build the request URL for upcoming movies
            String requestUrl =  apiUrl+ "&api_key=" + apiKey+"&language=en-US&page=1 ";

            Request request = new Request.Builder()
                    .url(requestUrl)
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                    .build();

            // Execute the API call
            Response response = client.newCall(request).execute();

            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray results = jsonObject.getJSONArray("results");

                // Parse each movie from the response
                for (int i = 0; i < results.length(); i++) {
                    JSONObject movieJson = results.getJSONObject(i);

                    JSONArray genreJsonArray = movieJson.optJSONArray("genre_ids");
                    List<Integer> genreIds = new ArrayList<>();
                    if (genreJsonArray != null) {
                        for (int j = 0; j < genreJsonArray.length(); j++) {
                            genreIds.add(genreJsonArray.getInt(j));
                        }
                    }

                    // Create a Movie object
                    Movie movie = new Movie(
                            movieJson.optString("poster_path"),
                            movieJson.optBoolean("adult", false),
                            movieJson.optString("overview"),
                            movieJson.optString("release_date"),
                            genreIds,
                            movieJson.optInt("id"),
                            movieJson.optString("original_language"),
                            movieJson.optString("title"),
                            movieJson.optString("backdrop_path"),
                            movieJson.optFloat("popularity", 0),
                            movieJson.optInt("vote_count", 0),
                            movieJson.optBoolean("video", false),
                            movieJson.optFloat("vote_average", 0)
                    );
                    movies.add(movie);
                }
            } else {
                System.out.println("API request failed with code: " + response.code());
            }
        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movie> searchByDirector(String favDirector) {
        String directorId = "0";
        List<Movie> movies = new ArrayList<>();
        try {
            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/search/person?query=" + favDirector +
                            "&include_adult=false&language=en-US&page=1" + "&api_key=" + apiKey)
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ODg3MTdhYjE2YjY5MmJjMjhlNTU0MzA5MDYxNTkzZiIsIm5iZiI6MTczMTIzNDg0NS43NDM4NTIxLCJzdWIiOiI2NzMwMjM2ODU5MDM2ZDJiY2YwOTAzOTgiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.PaOY-5JX5RkcffDVGUBwNaR_Sz-IEt0yCzimtuuYXng")
                    .build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray results = jsonObject.getJSONArray("results");
                JSONObject directorJSON = results.getJSONObject(0);
                directorId = directorJSON.optString("id");
            } else {
                System.out.println("API request failed with code: " + response.code());
            }
        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
        }

        try {
            // Build the request URL for upcoming movies
            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/person/"+ directorId + "/movie_credits?api_key=" + apiKey)
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                    .build();

            // Execute the API call
            Response response = client.newCall(request).execute();

            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray results = jsonObject.getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    JSONObject movieJson = results.getJSONObject(i);

                    JSONArray genreJsonArray = movieJson.getJSONArray("genre_ids");
                    List genre_ids = new ArrayList();
                    int size = genreJsonArray.length();
                    int j = 0;
                    while (j< size){
                        genre_ids.add(genreJsonArray.get(j));
                        j++;
                    }

                    Movie movie = new Movie(
                            movieJson.optString("poster_path"),
                            movieJson.getBoolean("adult"),
                            movieJson.getString("overview"),
                            movieJson.getString("release_date"),
                            genre_ids,
                            movieJson.getInt("id"),
                            movieJson.getString("original_language"),
                            movieJson.getString("title"),
                            movieJson.optString("backdrop_path"),
                            movieJson.getFloat("popularity"),
                            movieJson.getInt("vote_count"),
                            movieJson.getBoolean("video"),
                            movieJson.getFloat("vote_average")
                    );
                    movies.add(movie);
                }
            } else {
                System.out.println("API request failed with code: " + response.code());
            }
        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
        }

        return movies;




    }



}
