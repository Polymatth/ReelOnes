package app;

import data_access.MovieAPIAccess;
import use_case.search_movie.SearchMovieDataAccessInterface;

public class AppConfig {
    private static final String API_KEY = "688717ab16b692bc28e554309061593f";
    private static final String API_URL = "https://api.themoviedb.org/3/search/movie";

    public SearchMovieDataAccessInterface getMovieDataAccess() {
        return new MovieAPIAccess(API_KEY, API_URL);
    }
}
