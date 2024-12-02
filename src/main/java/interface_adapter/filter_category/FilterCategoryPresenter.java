package interface_adapter.filter_category;

import interface_adapter.ViewManagerModel;
import interface_adapter.filter_categories.FilterCategoriesState;
import interface_adapter.filter_categories.FilterCategoriesViewModel;
import use_case.filter_application.FilterApplicationOutputBoundary;
import use_case.filter_application.FilterApplicationOutputData;
import use_case.filter_category_selection.FilterCategorySelectionOutputBoundary;
import use_case.filter_category_selection.FilterCategorySelectionOutputData;

/**
 * The presenter for the Filter Category use cases: Filter Category Selection and Filter Application
 */
public class FilterCategoryPresenter implements FilterCategorySelectionOutputBoundary, FilterApplicationOutputBoundary {

    private FilterCategoryViewModel filterCategoryViewModel;
    private ViewManagerModel viewManagerModel;
    private FilterCategoriesViewModel filterCategoriesViewModel;

    public FilterCategoryPresenter(FilterCategoryViewModel filterCategoryViewModel, FilterCategoriesViewModel
            filterCategoriesViewModel, ViewManagerModel viewManagerModel) {
        this.filterCategoryViewModel = filterCategoryViewModel;
        this.viewManagerModel = viewManagerModel;
        this.filterCategoriesViewModel = filterCategoriesViewModel;
    }

    @Override
    public void prepareSuccessView(FilterCategorySelectionOutputData filterCategorySelectionOutputData) {
        //get the current state of the Filter Category view model.
        final FilterCategoryState filterCategoryState = filterCategoryViewModel.getState();
        //Set the appropriate instance variable values for the Filter Category State
        filterCategoryState.setCategoryName(filterCategorySelectionOutputData.getCategoryName());
        filterCategoryState.setCategoryOptions(filterCategorySelectionOutputData.getCategoryOptions());
        filterCategoryState.setFilteredList(filterCategorySelectionOutputData.getFilteredList());
        filterCategoryState.setOriginalList(filterCategorySelectionOutputData.getOriginalList());
        filterCategoryState.setSelectedOptions(filterCategorySelectionOutputData.getSelectedOptions());
        //Update the State in the Filter Category View Model.
        this.filterCategoryViewModel.setState(filterCategoryState);
        this.filterCategoryViewModel.firePropertyChanged();
        //Update the View Manager Model.
        this.viewManagerModel.setState(filterCategoryViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void updateFilteredList(FilterApplicationOutputData filterApplicationOutputData) {
        //get the current state of the Filter Category view model.
        final FilterCategoryState filterCategoryState = filterCategoryViewModel.getState();
        //Set the appropriate instance variable values for the Filter Category State
        filterCategoryState.setFilteredList(filterApplicationOutputData.getApplicableMovies());
        filterCategoryState.setSelectedOptions(filterApplicationOutputData.getOptionsSelected());
        //Update the State in the Filter Category View Model.
        this.filterCategoryViewModel.setState(filterCategoryState);
        this.filterCategoryViewModel.firePropertyChanged();

        final FilterCategoriesState filterCategoriesState = filterCategoriesViewModel.getState();
        filterCategoriesState.setMoviestoFilter(filterCategoryState.getCategoryName(),
                filterApplicationOutputData.getApplicableMovies());
        filterCategoriesState.setSelectedFilters(filterCategoryState.getCategoryName(),
                filterCategoryState.getSelectedOptions());

        filterCategoriesViewModel.setState(filterCategoriesState);
        filterCategoriesViewModel.firePropertyChanged();
        //No need to update view model at this point; we are still on the same view.
        this.viewManagerModel.setState(filterCategoriesViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
