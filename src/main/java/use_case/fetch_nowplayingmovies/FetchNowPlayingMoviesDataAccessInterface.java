package use_case.fetch_nowplayingmovies;

import entity.Movie;

import java.util.List;

public interface FetchNowPlayingMoviesDataAccessInterface {

    List<Movie> getNowPlayingMovies();
}
