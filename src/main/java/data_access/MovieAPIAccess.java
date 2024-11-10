package data_access;

import entity.Movie;
import use_case.search_movie.SearchMovieDataAccessInterface;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of SearchMovieDataAccessInterface to fetch movie data from API
 */
public class MovieAPIAccess implements SearchMovieDataAccessInterface {

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
}
