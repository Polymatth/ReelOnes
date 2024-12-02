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

    /**
     * Executes the FilterCategorySelection use case
     * @param categoryName the name of the filter category that the user selected
     * @param categoryOptions the list of options that are available within the selected filter category
     * @param originalList the list of movies that the user wants to filter through
     * @param filteredList the list of movies that are applicable to the currently selected options within that category
     * @param selectedFilters the list of currently selected options within that filter category
     */
    public void executeFilterCategorySelection(String categoryName, String[] categoryOptions, List<Movie> originalList,
                                               List<Movie> filteredList, List<String> selectedFilters) {
        final FilterCategorySelectionInputData filterCategorySelectionInputData = new FilterCategorySelectionInputData(
                categoryName, categoryOptions, originalList, filteredList, selectedFilters);
        filterCategorySelectionUseCaseInteractor.execute(filterCategorySelectionInputData);
    }

    /**
     * Executes the Filter Application use case.
     * @param categoryName the name of the category that the user has selected and wants to apply filters for
     * @param optionsSelected the filter options the user has selected within that category and wants to apply to the
     *                        list
     * @param allOptions all the options available within that filter category
     * @param originalList the movie list that the user wants to filter through
     */
    public void executeFilterApplication(String categoryName, List<String> optionsSelected, List<String> allOptions,
                                         List<Movie> originalList) {
        final FilterApplicationInputData filterApplicationInputData = new FilterApplicationInputData(categoryName,
                optionsSelected, allOptions, originalList);
        filterApplicationUseCaseInteractor.execute(filterApplicationInputData);
    }

}
