package use_case.go_to_filter_categories;

import entity.Movie;

import java.util.List;
import java.util.Map;

public class GoToFilterCategoriesInteractor implements GoToFilterCategoriesInputBoundary {
    private GoToFilterCategoriesOutputBoundary goToFilterCategoriesPresenter;

    public GoToFilterCategoriesInteractor(GoToFilterCategoriesOutputBoundary goToFilterCategoriesPresenter) {
        this.goToFilterCategoriesPresenter = goToFilterCategoriesPresenter;
    }

    public void execute(GoToFilterCategoriesInputData goToFilterCategoriesInputData) {
        List<Movie> originalList = goToFilterCategoriesInputData.getOriginalList();
        Map<String, List<Movie>> filtersToMovies = goToFilterCategoriesInputData.getFiltersToMovies();
        Map<String, List<String>> filtersToSelections = goToFilterCategoriesInputData.getFiltersToSelections();
        String listView = goToFilterCategoriesInputData.getListView();
        final GoToFilterCategoriesOutputData goToFilterCategoriesOutputData = new GoToFilterCategoriesOutputData(
                originalList, filtersToMovies, filtersToSelections, listView);
        this.goToFilterCategoriesPresenter.goToFilterCategories(goToFilterCategoriesOutputData);
    }
}
