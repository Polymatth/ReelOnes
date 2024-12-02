package app;

import data_access.MovieAPIAccess;
import use_case.fetch_popularmovies.FetchPopularMoviesDataAccessInterface;
import use_case.filter_application.FilterApplicationDataAccessInterface;
import use_case.movie_detail.MovieDetailDataAccessInterface;
import use_case.search_movie.SearchMovieDataAccessInterface;
import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesDataAccessInterface;

public class AppConfig {
    private static final String API_KEY = "688717ab16b692bc28e554309061593f";
    private static final String API_URL = "https://api.themoviedb.org/3/search/movie";
    private static final String API_URL_NOWPLAYING = "https://api.themoviedb.org/3/movie/now_playing?api_key=";
    private static final String API_URL_POPULAR = "https://api.themoviedb.org/3/movie/popular?";

    public SearchMovieDataAccessInterface getMovieDataAccess() {
        return new MovieAPIAccess(API_KEY, API_URL);
    }

    public FetchNowPlayingMoviesDataAccessInterface getNowPlayingMovieDataAccess() {return new MovieAPIAccess(API_KEY, API_URL_NOWPLAYING);}

    public FetchPopularMoviesDataAccessInterface getPopularMoviesDataAccess() {return new MovieAPIAccess(API_KEY,API_URL_POPULAR);}

    public MovieDetailDataAccessInterface getMovieDetailDataAccess() {
        return new MovieAPIAccess(API_KEY, API_URL);
    }

    public FilterApplicationDataAccessInterface getFilterApplicationDataAccess() {
        return new MovieAPIAccess(API_KEY, API_URL);
    }
}
