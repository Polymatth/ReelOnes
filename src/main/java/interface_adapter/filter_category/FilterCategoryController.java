package interface_adapter.filter_category;

import java.util.List;
import java.util.Map;

import use_case.filter_application.FilterApplicationInputBoundary;
import use_case.filter_application.FilterApplicationInputData;
import use_case.filter_category_selection.FilterCategorySelectionInputData;
import use_case.filter_category_selection.FilterCategorySelectionInputBoundary;
import entity.Movie;

/**
 * Controller for the Filter Category Selection Use Case.
 */
public class FilterCategoryController {

    private final FilterCategorySelectionInputBoundary filterCategorySelectionUseCaseInteractor;
    private final FilterApplicationInputBoundary filterApplicationUseCaseInteractor;

    public FilterCategoryController(FilterCategorySelectionInputBoundary filterCategorySelectionUseCaseInteractor,
                                    FilterApplicationInputBoundary filterApplicationUseCaseInteractor) {
        this.filterCategorySelectionUseCaseInteractor = filterCategorySelectionUseCaseInteractor;
        this.filterApplicationUseCaseInteractor = filterApplicationUseCaseInteractor;

    }

    public void executeFilterCategorySelection(String categoryName, String[] categoryOptions, List<Movie> originalList,
                                               List<Movie> filteredList, List<String> selectedFilters) {
        final FilterCategorySelectionInputData filterCategorySelectionInputData = new FilterCategorySelectionInputData(
                categoryName, categoryOptions, originalList, filteredList, selectedFilters);
        filterCategorySelectionUseCaseInteractor.execute(filterCategorySelectionInputData);
    }

    public void executeFilterApplication(String categoryName, List<String> optionsSelected, List<String> allOptions,
                                         List<Movie> originalList) {
        final FilterApplicationInputData filterApplicationInputData = new FilterApplicationInputData(categoryName,
                optionsSelected, allOptions, originalList);
        filterApplicationUseCaseInteractor.execute(filterApplicationInputData);
    }
}
