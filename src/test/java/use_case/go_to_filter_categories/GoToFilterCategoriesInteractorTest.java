package use_case.go_to_filter_categories;

import app.AppConfig;
import entity.Movie;
import org.junit.jupiter.api.Test;
import use_case.filter_application.FilterCategoryConstants;
import use_case.search_movie.SearchMovieDataAccessInterface;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class GoToFilterCategoriesInteractorTest {

    @Test
    void GoToFilterCategoriesNoInInitialFilters() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        //Create the list that the user wants to filter.
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        //Set the filters that have been selected and the movies that meet the filters for each category; in this case,
        //there are no selected filters and no movies that are filtered in.
        Map<String, List<Movie>> filtersToMovies = new HashMap<>();
        Map<String, List<String>> filtersToSelections = new HashMap<>();
        for (String category : FilterCategoryConstants.getCategories()) {
            filtersToMovies.put(category ,testList);
            filtersToSelections.put(category, new ArrayList<>());
        }
        GoToFilterCategoriesInputData goToFilterCategoriesInputData = new GoToFilterCategoriesInputData(testList,
                filtersToMovies, filtersToSelections, "movie search");

        //Create a test presenter that tests that the interactor works as we expect.
        GoToFilterCategoriesOutputBoundary goToFilterCategoriesPresenter = new GoToFilterCategoriesOutputBoundary() {
            @Override
            public void goToFilterCategories(GoToFilterCategoriesOutputData goToFilterCategoriesOutputData) {
                //Test that the original, unfiltered list has been set as the original list.
                assertEquals(testList, goToFilterCategoriesOutputData.getOriginalList());
                //Test that each key in filtersToMovies has an empty list as its value and that each key in
                // filtersToSelections has an empty list as its value.
                for (String category : FilterCategoryConstants.getCategories()) {
                    assert(goToFilterCategoriesOutputData.getFiltersToMovies().get(category).isEmpty());
                    assert(goToFilterCategoriesOutputData.getFiltersToSelections().get(category).isEmpty());
                }
            }
        };
        GoToFilterCategoriesInputBoundary interactor = new GoToFilterCategoriesInteractor(goToFilterCategoriesPresenter);
        interactor.execute(goToFilterCategoriesInputData);
    }
}
