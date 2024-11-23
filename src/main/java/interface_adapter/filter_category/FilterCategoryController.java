package interface_adapter.filter_category;

import java.util.List;
import use_case.filter_category_selection.FilterCategorySelectionInputData;
import use_case.filter_category_selection.FilterCategorySelectionInputBoundary;
import entity.Movie;

/**
 * Controller for the Filter Category Selection Use Case.
 */
public class FilterCategoryController {

    private final FilterCategorySelectionInputBoundary filterCategorySelectionUseCaseInteractor;

    public FilterCategoryController(FilterCategorySelectionInputBoundary filterCategorySelectionUseCaseInteractor) {
        this.filterCategorySelectionUseCaseInteractor = filterCategorySelectionUseCaseInteractor;
    }

    public void execute(String categoryName, String[] categoryOptions, List<Movie> originalList) {
        final FilterCategorySelectionInputData filterCategorySelectionInputData = new FilterCategorySelectionInputData(
                categoryName, categoryOptions, originalList);
        filterCategorySelectionUseCaseInteractor.execute(filterCategorySelectionInputData);
    }
}
