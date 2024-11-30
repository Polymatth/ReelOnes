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

    public void goToFilterCategoriesView(List<Movie> originalList, Map<String, List<Movie>> filtersToMovies,
                                         Map<String, List<String>> filtersToSelections, String listView) {
        final GoToFilterCategoriesInputData goToFilterCategoriesInputData = new GoToFilterCategoriesInputData(
                originalList, filtersToMovies, filtersToSelections, listView);
        goToFilterCategoriesInteractor.execute(goToFilterCategoriesInputData);
    }

    public void executeReturnToFilterCategoriesView(String categoryName, List<String> selectedOptions,
                                                    List<Movie> filteredList) {
        final ReturnToFilterCategoriesInputData returnToFilterCategoriesInputData = new
                ReturnToFilterCategoriesInputData(categoryName, selectedOptions, filteredList);
        returnToFilterCategoriesInteractor.execute(returnToFilterCategoriesInputData);
    }

    public void executeClearAllFilters(Map<String, List<String>> filtersToSelections, Map<String, List<Movie>>
            filtersToMovies, List<Movie> originalMovieList) {
        final ClearAllFiltersInputData clearAllFiltersInputData = new ClearAllFiltersInputData(filtersToSelections,
                filtersToMovies, originalMovieList);
        clearAllFiltersInteractor.execute(clearAllFiltersInputData);
    }

    public void executeReturnToList(Map<String, List<String>> filtersToSelections, Map<String, List<Movie>>
            filtersToMovies, String listView) {
        final ReturnToListInputData returnToListInputData = new ReturnToListInputData(filtersToSelections, filtersToMovies,
                listView);
        returnToListInteractor.execute(returnToListInputData);
    }
}
