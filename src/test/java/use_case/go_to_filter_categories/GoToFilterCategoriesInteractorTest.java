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
        //there are no selected filters and no movies that are filtered out.
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
                //Test that each key in filtersToMovies has the original list as its value and that each key in
                // filtersToSelections has an empty list as its value.
                for (String category : FilterCategoryConstants.getCategories()) {
                    assertEquals(testList.size(),
                            goToFilterCategoriesOutputData.getFiltersToMovies().get(category).size());
                    assert(goToFilterCategoriesOutputData.getFiltersToSelections().get(category).isEmpty());
                }
                //Test that the list view is correct.
                assertEquals("movie search", goToFilterCategoriesOutputData.getListView());
            }
        };
        GoToFilterCategoriesInputBoundary interactor = new GoToFilterCategoriesInteractor(goToFilterCategoriesPresenter);
        interactor.execute(goToFilterCategoriesInputData);
    }

    void GoToFilterCategoriesSomeInitialFiltersSelected() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        //Create the list that the user wants to filter.
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        //Set the filters that have been selected and the movies that meet the filters for each category; in this case,
        //there are no selected filters and no movies that are filtered out.
        Map<String, List<Movie>> filtersToMovies = new HashMap<>();
        Map<String, List<String>> filtersToSelections = new HashMap<>();
        for (String category : FilterCategoryConstants.getCategories()) {
            filtersToMovies.put(category,testList);
            filtersToSelections.put(category, new ArrayList<>());
        }
        filtersToSelections.put(FilterCategoryConstants.POPULARITY_RATING, Arrays.asList(new String[]{"5.0-5.9",
                "6.0-6.9"}));
        List<Movie> moviesToAddPopularity = new ArrayList<>();
        filtersToSelections.put(FilterCategoryConstants.GENRE, Arrays.asList(new String[]{"Documentary"}));
        List<Movie> moviesToAddGenre = new ArrayList<>();
        for (Movie movie : testList) {
            if (((float)5.0 <= movie.getVoteAverage() && (float)5.9>=movie.getVoteAverage()) ||
                    ((float)6.0 <=movie.getVoteAverage()&&(float)6.9>=movie.getVoteAverage())) {
                moviesToAddPopularity.add(movie);
            }
            if (movie.getGenre_ids().contains(99)) {
                moviesToAddGenre.add(movie);
            }
        }
        filtersToMovies.put(FilterCategoryConstants.POPULARITY_RATING, moviesToAddPopularity);
        filtersToMovies.put(FilterCategoryConstants.GENRE, moviesToAddGenre);

        GoToFilterCategoriesInputData goToFilterCategoriesInputData = new GoToFilterCategoriesInputData(testList,
                filtersToMovies, filtersToSelections, "movie search");

        //Create a test presenter that tests that the interactor works as we expect.
        GoToFilterCategoriesOutputBoundary goToFilterCategoriesPresenter = new GoToFilterCategoriesOutputBoundary() {
            @Override
            public void goToFilterCategories(GoToFilterCategoriesOutputData goToFilterCategoriesOutputData) {
                //Test that the keys "Genre" and "Popularity Ratings" contain the correct values in both filtersToMovies
                //and filtersToSelection
                assertEquals(filtersToMovies.get(FilterCategoryConstants.GENRE),
                        goToFilterCategoriesOutputData.getFiltersToMovies().get(FilterCategoryConstants.GENRE));
                assertEquals(filtersToMovies.get(FilterCategoryConstants.POPULARITY_RATING),
                        goToFilterCategoriesOutputData.getFiltersToMovies().
                                get(FilterCategoryConstants.POPULARITY_RATING));
                // Test that for the remaining keys, the value in filtersToMovies is the original list and the value in
                // filtersToSelections is an empty list.
                for (String category : FilterCategoryConstants.getCategories()) {
                    if ((!category.equals(FilterCategoryConstants.POPULARITY_RATING)) &&
                            (!category.equals(FilterCategoryConstants.GENRE))) {
                        assertEquals(testList.size(),
                                goToFilterCategoriesOutputData.getFiltersToMovies().get(category).size());
                        assert (goToFilterCategoriesOutputData.getFiltersToSelections().get(category).isEmpty());
                    }
                }
                //Test that the list view is correct.
                assertEquals("movie search", goToFilterCategoriesOutputData.getListView());
            }
        };
        GoToFilterCategoriesInputBoundary interactor = new GoToFilterCategoriesInteractor(goToFilterCategoriesPresenter);
        interactor.execute(goToFilterCategoriesInputData);
    }
}
