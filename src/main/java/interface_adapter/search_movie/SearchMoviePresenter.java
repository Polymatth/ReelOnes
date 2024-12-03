package interface_adapter.search_movie;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedInViewModel;
import use_case.filter_application.FilterCategoryConstants;
import use_case.search_movie.SearchMovieOutputBoundary;
import use_case.search_movie.SearchMovieOutputData;

import java.util.ArrayList;

public class SearchMoviePresenter implements SearchMovieOutputBoundary {
    private final SearchMovieViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public SearchMoviePresenter(ViewManagerModel viewManagerModel, SearchMovieViewModel viewModel, LoggedInViewModel loggedInViewModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(SearchMovieOutputData outputData) {
        this.viewModel.getState().setMovies(outputData.getMovies());
        for (String category : FilterCategoryConstants.getCategories()) {
            this.viewModel.getState().getFiltersToMovies().put(category, outputData.getMovies());
            this.viewModel.getState().getFiltersToSelections().put(category, new ArrayList<>());
        }
        this.viewModel.getState().setCurrentFilteredMovies(new ArrayList<>());
        if (this.viewModel.getState().getFiltered()) {
            this.viewModel.getState().setFiltered(false);
        }
        this.viewModel.firePropertyChanged();

        this.viewManagerModel.setState(viewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        viewModel.setErrorMessage(errorMessage);
        viewModel.firePropertyChanged("error");
    }
     public void switchToMainView() {
         viewManagerModel.setState(loggedInViewModel.getViewName());
         viewManagerModel.firePropertyChanged();
     }
}