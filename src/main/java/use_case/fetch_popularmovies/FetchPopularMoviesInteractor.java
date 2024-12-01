package use_case.fetch_popularmovies;

import entity.Movie;
import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesDataAccessInterface;
import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesOutputBoundary;
import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesOutputData;

import java.util.List;

public class FetchPopularMoviesInteractor implements FetchPopularMoviesInputBoundary{

    private final FetchPopularMoviesOutputBoundary presenter;
    private final FetchPopularMoviesDataAccessInterface dataAccessInterface;

    public FetchPopularMoviesInteractor(FetchPopularMoviesOutputBoundary presenter,  FetchPopularMoviesDataAccessInterface dataAccessInterface) {
        this.presenter = presenter;
        this.dataAccessInterface = dataAccessInterface;
    }
    @Override
    public void fetchPopularMovies() {
        try {
            List<Movie> movies = dataAccessInterface.getPopularMovies();
            FetchPopularMoviesOutputData outputData = new FetchPopularMoviesOutputData(movies);
            presenter.prepareSuccessView(outputData);
        } catch (Exception e) {
            presenter.prepareFailView("Failed to retrieve movies.");
        }

    }

}
