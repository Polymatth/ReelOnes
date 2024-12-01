package use_case.return_to_list_from_filter_categories;

import app.AppConfig;
import entity.Movie;
import org.junit.jupiter.api.Test;
import use_case.filter_application.FilterCategoryConstants;
import use_case.search_movie.SearchMovieDataAccessInterface;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ReturnToListInteractorTest {

    @Test
    void ReturnToListOneFilterCategorySelected() {
        //Create the list to be filtered.
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        //Note: The one filter category will be Popularity Ratings.
        //Set the selected options for that filter category and the applicable movies for the selected filter.
        Map<String, List<String>> filtersToSelections = new HashMap<>();
        filtersToSelections.put(FilterCategoryConstants.POPULARITY_RATING, Arrays.asList(new String[]{"5.0-5.9",
                "6.0-6.9"}));
        Map<String, List<Movie>> filtersToMovies = new HashMap<>();
        List<Movie> moviesToAdd = new ArrayList<>();
        for (Movie movie : testList) {
            float avg = movie.getVote_average();
            if (((float)5.0 <= avg && (float)5.9 >= avg) || ((float)6.0 <= avg && (float)6.9 >= avg)) {
                moviesToAdd.add(movie);
            }
        }

        filtersToMovies.put(FilterCategoryConstants.POPULARITY_RATING, moviesToAdd);
        for (String category : FilterCategoryConstants.getCategories()) {
            if (!category.equals(FilterCategoryConstants.POPULARITY_RATING)) {
                filtersToMovies.put(category, testList);
                filtersToSelections.put(category, new ArrayList<>());
            }
        }
        //Set the list view to return to.
        String listView = "movie search";
        ReturnToListInputData returnToListInputData = new ReturnToListInputData(filtersToSelections, filtersToMovies,
                listView);
        //Create a test presenter that tests that the interactor works as we expect.
        ReturnToListOutputBoundary returnToListPresenter = new ReturnToListOutputBoundary() {
            @Override
            public void executeReturnToList(ReturnToListOutputData returnToListOutputData) {
                //Test that the final Filtered List has 4 items in it.
                assertEquals(4, returnToListOutputData.getFinalFilteredList().size());
                //Test that the list contains the correct movies (i.e. "The Substance: Albert Hofmann's LSD",
                // "The Substance of Fire", "The Night's Substance", "A Magical Substance Flows Into Me"
                List<String> expectedTitle = Arrays.asList(new String[]{"The Substance: Albert Hofmann's LSD",
                        "The Substance of Fire", "The Night's Substance", "A Magical Substance Flows Into Me"});
                for (Movie movie : returnToListOutputData.getFinalFilteredList()) {
                    assert(expectedTitle.contains(movie.getTitle()));
                }
                //Test that the map of filters to movies has remained the same.
                assertEquals(returnToListInputData.getFiltersToSelections(),
                        returnToListOutputData.getFiltersToSelections());
                //Test that the map of filters to selections has remained the same.
                assertEquals(returnToListInputData.getFiltersToMovies(), returnToListOutputData.getFiltersToMovies());
                //Test that the list view has remained the same.
                assertEquals(returnToListInputData.getListView(), returnToListOutputData.getListView());
            }
        };
        ReturnToListInputBoundary interactor = new ReturnToListInteractor(returnToListPresenter);
        interactor.execute(returnToListInputData);
    }

    @Test
    void ReturnToListTwoFilterCategoriesSelectedFinalListNotEmpty() {
        //Create the list to be filtered.
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        //Note: The one filter category will be Popularity Ratings and Genre.
        //Set the selected options for that filter category and the applicable movies for the Popularity Ratings filter
        //and the Genre filter.
        Map<String, List<String>> filtersToSelections = new HashMap<>();
        filtersToSelections.put(FilterCategoryConstants.POPULARITY_RATING, Arrays.asList(new String[]{"5.0-5.9",
                "6.0-6.9"}));
        Map<String, List<Movie>> filtersToMovies = new HashMap<>();
        List<Movie> moviesToAddPopularity = new ArrayList<>();
        filtersToSelections.put(FilterCategoryConstants.GENRE, Arrays.asList(new String[]{"Documentary"}));
        List<Movie> moviesToAddGenre = new ArrayList<>();
        for (Movie movie : testList) {
            if (((float)5.0 <= movie.getVote_average() && (float)5.9>=movie.getVote_average()) ||
                    ((float)6.0 <=movie.getVote_average()&&(float)6.9>=movie.getVote_average())) {
                moviesToAddPopularity.add(movie);
            }
            if (movie.getGenre_ids().contains(99)) {
                moviesToAddGenre.add(movie);
            }
        }
        filtersToMovies.put(FilterCategoryConstants.POPULARITY_RATING, moviesToAddPopularity);
        filtersToMovies.put(FilterCategoryConstants.GENRE, moviesToAddGenre);

        for (String category : FilterCategoryConstants.getCategories()) {
            if (!category.equals(FilterCategoryConstants.POPULARITY_RATING) &&
                    !category.equals(FilterCategoryConstants.GENRE)) {
                filtersToMovies.put(category, testList);
                filtersToSelections.put(category, new ArrayList<>());
            }
        }

        //Set the list view to return to.
        String listView = "movie search";
        ReturnToListInputData returnToListInputData = new ReturnToListInputData(filtersToSelections, filtersToMovies,
                listView);
        //Create a test presenter that tests that the interactor works as we expect.
        ReturnToListOutputBoundary returnToListPresenter = new ReturnToListOutputBoundary() {
            @Override
            public void executeReturnToList(ReturnToListOutputData returnToListOutputData) {
                //Test that the final Filtered List has 2 items in it.
                assertEquals(2, returnToListOutputData.getFinalFilteredList().size());
                //Test that the list contains the correct movies (i.e. "The Substance: Albert Hofmann's LSD",
                // "A Magical Substance Flows Into Me"
                List<String> expectedTitle = Arrays.asList(new String[]{"The Substance: Albert Hofmann's LSD",
                        "A Magical Substance Flows Into Me"});
                for (Movie movie : returnToListOutputData.getFinalFilteredList()) {
                    assert(expectedTitle.contains(movie.getTitle()));
                }
                //Test that the map of filters to movies has remained the same.
                assertEquals(returnToListInputData.getFiltersToSelections(),
                        returnToListOutputData.getFiltersToSelections());
                //Test that the map of filters to selections has remained the same.
                assertEquals(returnToListInputData.getFiltersToMovies(), returnToListOutputData.getFiltersToMovies());
                //Test that the list view has remained the same.
                assertEquals(returnToListInputData.getListView(), returnToListOutputData.getListView());
            }
        };
        ReturnToListInputBoundary interactor = new ReturnToListInteractor(returnToListPresenter);
        interactor.execute(returnToListInputData);
    }

    @Test
    void ReturnToListTwoFilterCategoriesSelectedFinalListEmpty() {
        //Create the list to be filtered.
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        //Note: The one filter category will be Popularity Ratings and Genre.
        //Set the selected options for that filter category and the applicable movies for the Popularity Ratings filter
        //and the Genre filter.
        Map<String, List<String>> filtersToSelections = new HashMap<>();
        filtersToSelections.put(FilterCategoryConstants.POPULARITY_RATING, Arrays.asList(new String[]{"5.0-5.9",
                "6.0-6.9"}));
        Map<String, List<Movie>> filtersToMovies = new HashMap<>();
        List<Movie> moviesToAddPopularity = new ArrayList<>();
        filtersToSelections.put(FilterCategoryConstants.GENRE, Arrays.asList(new String[]{"Horror"}));
        List<Movie> moviesToAddGenre = new ArrayList<>();
        for (Movie movie : testList) {
            if (((float)5.0 <= movie.getVote_average() && (float)5.9>=movie.getVote_average()) ||
                    ((float)6.0 <=movie.getVote_average()&&(float)6.9>=movie.getVote_average())) {
                moviesToAddPopularity.add(movie);
            }
            if (movie.getGenre_ids().contains(27)) {
                moviesToAddGenre.add(movie);
            }
        }
        filtersToMovies.put(FilterCategoryConstants.POPULARITY_RATING, moviesToAddPopularity);
        filtersToMovies.put(FilterCategoryConstants.GENRE, moviesToAddGenre);

        for (String category : FilterCategoryConstants.getCategories()) {
            if (!category.equals(FilterCategoryConstants.POPULARITY_RATING) &&
                    !category.equals(FilterCategoryConstants.GENRE)) {
                filtersToMovies.put(category, testList);
                filtersToSelections.put(category, new ArrayList<>());
            }
        }

        //Set the list view to return to.
        String listView = "movie search";
        ReturnToListInputData returnToListInputData = new ReturnToListInputData(filtersToSelections, filtersToMovies,
                listView);
        //Create a test presenter that tests that the interactor works as we expect.
        ReturnToListOutputBoundary returnToListPresenter = new ReturnToListOutputBoundary() {
            @Override
            public void executeReturnToList(ReturnToListOutputData returnToListOutputData) {
                //Test that the final Filtered List has 0 items in it.
                assertEquals(0, returnToListOutputData.getFinalFilteredList().size());
                //Test that the map of filters to movies has remained the same.
                assertEquals(returnToListInputData.getFiltersToSelections(),
                        returnToListOutputData.getFiltersToSelections());
                //Test that the map of filters to selections has remained the same.
                assertEquals(returnToListInputData.getFiltersToMovies(), returnToListOutputData.getFiltersToMovies());
                //Test that the list view has remained the same.
                assertEquals(returnToListInputData.getListView(), returnToListOutputData.getListView());
            }
        };
        ReturnToListInputBoundary interactor = new ReturnToListInteractor(returnToListPresenter);
        interactor.execute(returnToListInputData);
    }

    @Test
    void ReturnToListNoFilterCategoriesSelected() {
        //Create the list to be filtered.
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        //Note: The one filter category will be Popularity Ratings.
        //Set the selected options for that filter category and the applicable movies for the selected filter.
        Map<String, List<String>> filtersToSelections = new HashMap<>();
        Map<String, List<Movie>> filtersToMovies = new HashMap<>();
        for (String category : FilterCategoryConstants.getCategories()) {
            if (!category.equals(FilterCategoryConstants.POPULARITY_RATING)) {
                filtersToMovies.put(category, testList);
                filtersToSelections.put(category, new ArrayList<>());
            }
        }
        //Set the list view to return to.
        String listView = "movie search";
        ReturnToListInputData returnToListInputData = new ReturnToListInputData(filtersToSelections, filtersToMovies,
                listView);
        //Create a test presenter that tests that the interactor works as we expect.
        ReturnToListOutputBoundary returnToListPresenter = new ReturnToListOutputBoundary() {
            @Override
            public void executeReturnToList(ReturnToListOutputData returnToListOutputData) {
                //Test that the final Filtered List has 4 items in it.
                assertEquals(16, returnToListOutputData.getFinalFilteredList().size());
                //Test that the list contains the correct movies, i.e. the same movies as in the original search list
                assertEquals(testList, returnToListOutputData.getFinalFilteredList());
                //Test that the map of filters to movies has remained the same.
                assertEquals(returnToListInputData.getFiltersToSelections(),
                        returnToListOutputData.getFiltersToSelections());
                //Test that the map of filters to selections has remained the same.
                assertEquals(returnToListInputData.getFiltersToMovies(), returnToListOutputData.getFiltersToMovies());
                //Test that the list view has remained the same.
                assertEquals(returnToListInputData.getListView(), returnToListOutputData.getListView());
            }
        };
        ReturnToListInputBoundary interactor = new ReturnToListInteractor(returnToListPresenter);
        interactor.execute(returnToListInputData);
    }
}
