package interface_adapter.fetch_popularmovies;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedInViewModel;
import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesOutputData;
import use_case.fetch_popularmovies.FetchPopularMoviesOutputBoundary;
import use_case.fetch_popularmovies.FetchPopularMoviesOutputData;

/**
 * Controller for the Fetch Popular  Movies Use Case
 */

public class FetchPopularMoviesPresenter implements FetchPopularMoviesOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public FetchPopularMoviesPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(FetchPopularMoviesOutputData outputData) {
        this.loggedInViewModel.getState().setPopularMovies(outputData.getMovies());
        this.loggedInViewModel.firePropertyChanged("popular");

    }

    @Override
    public void prepareFailView(String errorMessage) {
    }
}
