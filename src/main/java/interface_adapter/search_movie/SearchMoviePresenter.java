package interface_adapter.search_movie;

import interface_adapter.ViewManagerModel;
import use_case.search_movie.SearchMovieOutputBoundary;
import use_case.search_movie.SearchMovieOutputData;

public class SearchMoviePresenter implements SearchMovieOutputBoundary {
    private final SearchMovieViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchMoviePresenter(ViewManagerModel viewManagerModel, SearchMovieViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SearchMovieOutputData outputData) {
        this.viewModel.getState().setMovies(outputData.getMovies());
        this.viewModel.firePropertyChanged();

        this.viewManagerModel.setState(viewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        viewModel.setErrorMessage(errorMessage);
        viewModel.firePropertyChanged("error");
    }
}