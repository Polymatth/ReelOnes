package interface_adapter.filter_category;

import interface_adapter.ViewManagerModel;
import use_case.filter_category_selection.FilterCategorySelectionOutputBoundary;
import use_case.filter_category_selection.FilterCategorySelectionOutputData;

public class FilterCategoryPresenter implements FilterCategorySelectionOutputBoundary {

    private FilterCategoryViewModel filterCategoryViewModel;
    private ViewManagerModel viewManagerModel;

    public FilterCategoryPresenter(FilterCategoryViewModel filterCategoryViewModel, ViewManagerModel viewManagerModel) {
        this.filterCategoryViewModel = filterCategoryViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(FilterCategorySelectionOutputData filterCategorySelectionOutputData) {
        //get the current state of the Filter Category view model.
        final FilterCategoryState filterCategoryState = filterCategoryViewModel.getState();
        //Set the appropriate instance variable values for the Filter Category State
        filterCategoryState.setCategoryName(filterCategorySelectionOutputData.getCategoryName());
        filterCategoryState.setCategoryOptions(filterCategorySelectionOutputData.getCategoryOptions());
        //Update the State in the Filter Category View Model.
        this.filterCategoryViewModel.setState(filterCategoryState);
        this.filterCategoryViewModel.firePropertyChanged();
        //Update the View Manager Model.
        this.viewManagerModel.setState(filterCategoryViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
