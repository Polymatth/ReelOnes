package data_access;

import entity.Movie;
import use_case.movie_detail.MovieDetailDataAccessInterface;
import use_case.search_movie.SearchMovieDataAccessInterface;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of SearchMovieDataAccessInterface to fetch movie data from API
 */
public class MovieAPIAccess implements SearchMovieDataAccessInterface, MovieDetailDataAccessInterface {

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
                    while (i< size){
                        genre_ids.add(genreJsonArray.get(i));
                        i++;
                    }

                    Movie movie = new Movie(
                            movieJson.getString("poster_path"),
                            movieJson.getBoolean("adult"),
                            movieJson.getString("overview"),
                            movieJson.getString("release_date"),
                            genre_ids,
                            movieJson.getInt("id"),
                            movieJson.getString("original_language"),
                            movieJson.getString("title"),
                            movieJson.getString("backdrop_path"),
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

    public Map<Integer, String> getGenres() {
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

//    public List<String> getStreaming(int movieID) {
//        try {
//            OkHttpClient client = new OkHttpClient();

//            Request request = new Request.Builder()
//                    .url("https://api.themoviedb.org/3/movie/movie_id/watch/providers")
//                    .get()
//                    .addHeader("accept", "application/json")
//                    .addHeader("Authorization", "Bearer application/json")
//                    .build();

//            Response response = client.newCall(request).execute();
//            if (response.isSuccessful() && response.body() != null) {
//                String jsonResponse = response.body().string();
//                JSONObject jsonObject = new JSONObject(jsonResponse);
//                JSONArray results = jsonObject.getJSONArray("id");
//            }
//            } catch (IOException | org.json.JSONException e) {
//            e.printStackTrace();
//        }
 //   }
}
