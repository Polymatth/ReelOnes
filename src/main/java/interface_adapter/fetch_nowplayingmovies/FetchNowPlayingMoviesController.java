package interface_adapter.fetch_nowplayingmovies;

import entity.Movie;
import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesInputBoundary;
import use_case.search_movie.SearchMovieInputData;

import java.util.List;

/**
 * Controller for the Fetch Now Playing  Movies Use Case
 */

public class FetchNowPlayingMoviesController {
    private final FetchNowPlayingMoviesInputBoundary fetchNowPlayingMoviesInputBoundary;

    public FetchNowPlayingMoviesController(FetchNowPlayingMoviesInputBoundary fetchNowPlayingMoviesInputBoundary) {
        this.fetchNowPlayingMoviesInputBoundary = fetchNowPlayingMoviesInputBoundary;
    }

    /**
     * Executes the Fetch Now Playing Movies Use Case
     */
    public void execute() {
         fetchNowPlayingMoviesInputBoundary.fetchNowPlayingMovies();
    }


}


