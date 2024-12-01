package use_case.filter_category_selection;

import entity.Movie;

import java.util.List;
import java.util.Map;

/**
 * The filter category selection interactor
 */
public class FilterCategorySelectionInteractor implements FilterCategorySelectionInputBoundary {

    private FilterCategorySelectionOutputBoundary filterCategorySelectionPresenter;

    public FilterCategorySelectionInteractor(FilterCategorySelectionOutputBoundary filterCategorySelectionPresenter) {
        this.filterCategorySelectionPresenter = filterCategorySelectionPresenter;
    }

    @Override
    public void execute(FilterCategorySelectionInputData filterCategorySelectionInputData) {
        String categoryName = filterCategorySelectionInputData.getCategoryName();
        String[] categoryOptions = filterCategorySelectionInputData.getCategoryOptions();
        List<Movie> originalList = filterCategorySelectionInputData.getOriginalList();
        List<Movie> filteredList = filterCategorySelectionInputData.getFilteredList();
        List<String> selectedOptions = filterCategorySelectionInputData.getSelectedOptions();
        final FilterCategorySelectionOutputData filterCategorySelectionOutputData = new
                FilterCategorySelectionOutputData(categoryName, categoryOptions, originalList, filteredList,
                selectedOptions);
        this.filterCategorySelectionPresenter.prepareSuccessView(filterCategorySelectionOutputData);
        //note: there is no fail view because there is currently no way for this use case to fail.
    }
}
