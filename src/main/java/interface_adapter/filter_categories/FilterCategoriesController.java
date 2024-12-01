package interface_adapter.filter_categories;

import entity.Movie;
import use_case.clear_filters.ClearAllFiltersInputBoundary;
import use_case.clear_filters.ClearAllFiltersInputData;
import use_case.go_to_filter_categories.GoToFilterCategoriesInputBoundary;
import use_case.go_to_filter_categories.GoToFilterCategoriesInputData;
import use_case.return_to_filter_categories.ReturnToFilterCategoriesInputBoundary;
import use_case.return_to_filter_categories.ReturnToFilterCategoriesInputData;
import use_case.return_to_list_from_filter_categories.ReturnToListInputBoundary;
import use_case.return_to_list_from_filter_categories.ReturnToListInputData;


import java.util.List;
import java.util.Map;

/**
 * The Controller for Filter Categories Use Cases: Go To Filter Categories, Return to Filter Categories from Filter
 * Category, Clear All Filters, and Return To List.
 */
public class FilterCategoriesController {

    private final ReturnToFilterCategoriesInputBoundary returnToFilterCategoriesInteractor;
    private final GoToFilterCategoriesInputBoundary goToFilterCategoriesInteractor;
    private final ClearAllFiltersInputBoundary clearAllFiltersInteractor;
    private final ReturnToListInputBoundary returnToListInteractor;

    public FilterCategoriesController(ReturnToFilterCategoriesInputBoundary returnToFilterCategoriesInteractor,
                                      GoToFilterCategoriesInputBoundary goToFilterCategoriesInteractor,
                                      ClearAllFiltersInputBoundary clearAllFiltersInteractor,
                                      ReturnToListInputBoundary returnToListInteractor) {
        this.returnToFilterCategoriesInteractor = returnToFilterCategoriesInteractor;
        this.goToFilterCategoriesInteractor = goToFilterCategoriesInteractor;
        this.clearAllFiltersInteractor = clearAllFiltersInteractor;
        this.returnToListInteractor = returnToListInteractor;
    }

    /**
     * Executes the GoToFilterCategories use case.
     * @param originalList the list that the user wants to filter through.
     * @param filtersToMovies a map of the applicable movies for each filter
     * @param filtersToSelections a map of the selected options for each filter
     * @param listView the name of the view of the list that the user wants to filter through.
     */
    public void goToFilterCategoriesView(List<Movie> originalList, Map<String, List<Movie>> filtersToMovies,
                                         Map<String, List<String>> filtersToSelections, String listView) {
        final GoToFilterCategoriesInputData goToFilterCategoriesInputData = new GoToFilterCategoriesInputData(
                originalList, filtersToMovies, filtersToSelections, listView);
        goToFilterCategoriesInteractor.execute(goToFilterCategoriesInputData);
    }

    /**
     * Executes the ReturnToFilterCategories use case
     * @param categoryName the name of the filter category that the user was looking at previously
     * @param selectedOptions the list of options that the user selected within that filter category
     * @param filteredList the list of movies that satisfy the selected filters within that category
     */
    public void executeReturnToFilterCategoriesView(String categoryName, List<String> selectedOptions,
                                                    List<Movie> filteredList) {
        final ReturnToFilterCategoriesInputData returnToFilterCategoriesInputData = new
                ReturnToFilterCategoriesInputData(categoryName, selectedOptions, filteredList);
        returnToFilterCategoriesInteractor.execute(returnToFilterCategoriesInputData);
    }

    /**
     * Executes the ClearAllFilters use case.
     * @param filtersToSelections a map of the selected options for each filter category
     * @param filtersToMovies a map of the applicable movies within each filter category
     * @param originalMovieList the original list that the user wants to filter through
     */
    public void executeClearAllFilters(Map<String, List<String>> filtersToSelections, Map<String, List<Movie>>
            filtersToMovies, List<Movie> originalMovieList) {
        final ClearAllFiltersInputData clearAllFiltersInputData = new ClearAllFiltersInputData(filtersToSelections,
                filtersToMovies, originalMovieList);
        clearAllFiltersInteractor.execute(clearAllFiltersInputData);
    }

    /**
     * Executes the ReturnToList use case.
     * @param filtersToSelections a map of the selected options for each filter category
     * @param filtersToMovies a map of the applicable movies within each filter category
     * @param listView the name of the view of the list the user wants to return to
     */
    public void executeReturnToList(Map<String, List<String>> filtersToSelections, Map<String, List<Movie>>
            filtersToMovies, String listView) {
        final ReturnToListInputData returnToListInputData = new ReturnToListInputData(filtersToSelections, filtersToMovies,
                listView);
        returnToListInteractor.execute(returnToListInputData);
    }
}
