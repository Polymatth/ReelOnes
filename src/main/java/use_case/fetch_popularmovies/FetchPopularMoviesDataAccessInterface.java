package use_case.fetch_popularmovies;

import entity.Movie;

import java.util.List;

public interface FetchPopularMoviesDataAccessInterface {

    List<Movie> getPopularMovies();
}
