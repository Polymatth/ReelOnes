package interface_adapter.fetch_nowplayingmovies;

import entity.Movie;
import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedInViewModel;
import interface_adapter.search_movie.SearchMovieViewModel;
import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesOutputBoundary;
import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesOutputData;
import use_case.filter_application.FilterCategoryConstants;
import use_case.search_movie.SearchMovieOutputData;

import java.util.ArrayList;
import java.util.List;

public class FetchNowPlayingMoviesPresenter implements FetchNowPlayingMoviesOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public FetchNowPlayingMoviesPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(FetchNowPlayingMoviesOutputData outputData) {
        this.loggedInViewModel.getState().setNowPlayingMovies(outputData.getMovies());
        this.loggedInViewModel.firePropertyChanged("nowplaying");

    }

    @Override
    public void prepareFailView(String errorMessage) {
    }
}
