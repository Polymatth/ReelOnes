package interface_adapter.search_movie;

import use_case.search_movie.SearchMovieOutputBoundary;
import use_case.search_movie.SearchMovieOutputData;

public class SearchMoviePresenter implements SearchMovieOutputBoundary {
    private final SearchMovieViewModel viewModel;

    public SearchMoviePresenter(SearchMovieViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(SearchMovieOutputData outputData) {
        viewModel.setMovies(outputData.getMovies());
        viewModel.firePropertyChanged("searchResults");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        viewModel.setErrorMessage(errorMessage);
        viewModel.firePropertyChanged("error");
    }
}