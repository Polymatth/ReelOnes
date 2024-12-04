package interface_adapter.filter_categories;

import entity.Movie;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.open_list.OpenListState;
import interface_adapter.open_list.OpenListViewModel;
import interface_adapter.search_movie.SearchMovieState;
import interface_adapter.search_movie.SearchMovieViewModel;
import use_case.clear_filters.ClearAllFiltersOutputBoundary;
import use_case.clear_filters.ClearAllFiltersOutputData;
import use_case.go_to_filter_categories.GoToFilterCategoriesOutputBoundary;
import use_case.go_to_filter_categories.GoToFilterCategoriesOutputData;
import use_case.return_to_filter_categories.ReturnToFilterCategoriesOutputBoundary;
import use_case.return_to_filter_categories.ReturnToFilterCategoriesOutputData;
import use_case.return_to_list_from_filter_categories.ReturnToListOutputBoundary;
import use_case.return_to_list_from_filter_categories.ReturnToListOutputData;
import view.SearchMovieView;
import view.ViewManager;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;

/**
 * The presenter for the Filter Categories use cases: Go To Filter Categories, Return to Filter Categories from Filter
 * Category, Clear All Filters, and Return to List from Filter Categories
 */
public class FilterCategoriesPresenter implements ReturnToFilterCategoriesOutputBoundary,
        GoToFilterCategoriesOutputBoundary, ClearAllFiltersOutputBoundary, ReturnToListOutputBoundary {

    private FilterCategoriesViewModel filterCategoriesViewModel;
    private ViewManagerModel viewManagerModel;
    private List<ViewModel> possibleViewModels = new ArrayList<>();
    private SearchMovieViewModel searchMovieViewModel;

    public FilterCategoriesPresenter(FilterCategoriesViewModel filterCategoriesViewModel, SearchMovieViewModel
            searchMovieViewModel, ViewManagerModel viewManagerModel) {
        this.filterCategoriesViewModel = filterCategoriesViewModel;
        this.searchMovieViewModel = searchMovieViewModel;
        this.possibleViewModels.add(this.searchMovieViewModel);
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void goToFilterCategories(GoToFilterCategoriesOutputData goToFilterCategoriesOutputData) {
        //get the current state of the Filter Category view model.
        final FilterCategoriesState filterCategoriesState = filterCategoriesViewModel.getState();
        //Set the appropriate instance variable values for the Filter Categories State
        filterCategoriesState.setOriginalMovieList(goToFilterCategoriesOutputData.getOriginalList());
        //for (List<Movie> movieList : goToFilterCategoriesOutputData.getFiltersToMovies().values()) {
        //    if (!movieList.isEmpty()) {
        filterCategoriesState.setFilterToMovies(goToFilterCategoriesOutputData.getFiltersToMovies());
        //    }
        //}
        //for (List<String> optionsList : goToFilterCategoriesOutputData.getFiltersToSelections().values()) {
        //     if (!optionsList.isEmpty()) {
        filterCategoriesState.setFiltersToSelections(goToFilterCategoriesOutputData.getFiltersToSelections());
        //    }
        //}
        filterCategoriesState.setListView(goToFilterCategoriesOutputData.getListView());
        //Update the State in the Filter Category View Model.
        this.filterCategoriesViewModel.setState(filterCategoriesState);
        this.filterCategoriesViewModel.firePropertyChanged();
        //Update the View Manager Model.
        this.viewManagerModel.setState(filterCategoriesViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void executeReturnToFilterCategories(ReturnToFilterCategoriesOutputData returnToFilterCategoriesOutputData) {
        //get the current state of the Filter Category view model.
        final FilterCategoriesState filterCategoriesState = filterCategoriesViewModel.getState();
        //Set the appropriate instance variable values for the Filter Categories State
        filterCategoriesState.setMoviestoFilter(returnToFilterCategoriesOutputData.getCategoryName(),
                returnToFilterCategoriesOutputData.getFilteredMovies());
        filterCategoriesState.setSelectedFilters(returnToFilterCategoriesOutputData.getCategoryName(),
                returnToFilterCategoriesOutputData.getSelectedOptions());
        //Update the State in the Filter Category View Model.
        this.filterCategoriesViewModel.setState(filterCategoriesState);
        this.filterCategoriesViewModel.firePropertyChanged();
        //Update the View Manager Model.
        this.viewManagerModel.setState(filterCategoriesViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void executeClearAllFilters(ClearAllFiltersOutputData clearAllFiltersOutputData) {
        //get the current state of the Filter Category view model.
        final FilterCategoriesState filterCategoriesState = filterCategoriesViewModel.getState();
        //Set the appropriate instance variable values for the Filter Categories State
        filterCategoriesState.setFilterToMovies(clearAllFiltersOutputData.getFiltersToMovies());
        filterCategoriesState.setFiltersToSelections(clearAllFiltersOutputData.getFiltersToSelections());
        //Update the State in the Filter Category View Model.
        this.filterCategoriesViewModel.setState(filterCategoriesState);
        this.filterCategoriesViewModel.firePropertyChanged();
        //No need to update View Manager Model, as the view doesn't change.
    }

    @Override
    public void executeReturnToList(ReturnToListOutputData returnToListOutputData) {
        //Find the correct list view model using the list view name.
        ViewModel viewModel;
        if (returnToListOutputData.getListView().equals(this.searchMovieViewModel.getViewName())) {
            //get the current state of the Search Movie view model.
            viewModel = this.searchMovieViewModel;
            SearchMovieState state = this.searchMovieViewModel.getState();
            state.setFiltersToMovies(returnToListOutputData.getFiltersToMovies());
            state.setFiltersToSelections(returnToListOutputData.getFiltersToSelections());
            state.setCurrentFilteredMovies(returnToListOutputData.getFinalFilteredList());
            if (!state.getFiltered() && !returnToListOutputData.getFinalFilteredList().equals(state.getMovies())) {
                state.setFiltered(true);
            }
            //Update the State in the appropriate View Model.
            this.searchMovieViewModel.setState(state);
            this.searchMovieViewModel.firePropertyChanged();
        }
    }
}
