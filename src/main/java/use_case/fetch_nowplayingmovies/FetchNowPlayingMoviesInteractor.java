package use_case.fetch_nowplayingmovies;

import entity.Movie;
import use_case.search_movie.SearchMovieDataAccessInterface;
import use_case.search_movie.SearchMovieOutputBoundary;
import use_case.search_movie.SearchMovieOutputData;

import java.util.List;

/**
 * Interactor for the Fetch Now Playing Movies Use Case
 */

public class FetchNowPlayingMoviesInteractor implements FetchNowPlayingMoviesInputBoundary {

    private final FetchNowPlayingMoviesOutputBoundary presenter;
    private final FetchNowPlayingMoviesDataAccessInterface dataAccessInterface;

    public FetchNowPlayingMoviesInteractor(FetchNowPlayingMoviesOutputBoundary presenter, FetchNowPlayingMoviesDataAccessInterface dataAccessInterface) {
        this.presenter = presenter;
        this.dataAccessInterface = dataAccessInterface;
    }
    @Override
    public void fetchNowPlayingMovies() {
        try {
            List<Movie> movies = dataAccessInterface.getNowPlayingMovies();
            FetchNowPlayingMoviesOutputData outputData = new FetchNowPlayingMoviesOutputData(movies);
            presenter.prepareSuccessView(outputData);
        } catch (Exception e) {
            presenter.prepareFailView("Failed to retrieve movies.");
        }

    }
}
