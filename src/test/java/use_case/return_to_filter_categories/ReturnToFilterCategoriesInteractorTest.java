package use_case.return_to_filter_categories;

import app.AppConfig;
import entity.Movie;
import org.junit.jupiter.api.Test;
import use_case.go_to_filter_categories.*;
import use_case.search_movie.SearchMovieDataAccessInterface;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ReturnToFilterCategoriesInteractorTest {

    @Test
    void ReturnToFilterCategoriesSuccess() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        //Set the filter category.
        String categoryName = "Popularity Ratings";
        //Set the list of filtered movies.
        List<Movie> filteredMovies = new ArrayList<>();
        filteredMovies.add(new Movie("/htEeIySOOKsY6GCsE5YNohqbL9a.jpg", false, "Jaiane now " +
                "lives in Brazil, while Aissa, a Mozambican sailor who has just arrived in the city, tries to have a " +
                "real experience on dry land. A story of unconventional passion follows.", "2021-10-06",
                Arrays.asList(new Integer[]{18}), 874929, "pt", "The Night's Substance",
                "/d2DXhcnn6BNo9S9o7tOkn1lA7vI.jpg", (float)0.425, 2, false, (float)5.5));
        //Set the list of selected filter options.
        List<String> categoryselections = new ArrayList<>();
        categoryselections.add("5.0-5.9");
        ReturnToFilterCategoriesInputData returnToFilterCategoriesInputData = new ReturnToFilterCategoriesInputData(
                "Popularity Ratings", categoryselections, filteredMovies);

        //Create a test presenter that tests that the interactor works as we expect.
        ReturnToFilterCategoriesOutputBoundary returnToFilterCategoriesPresenter = new ReturnToFilterCategoriesOutputBoundary() {
            @Override
            public void executeReturnToFilterCategories(ReturnToFilterCategoriesOutputData returnToFilterCategoriesOutputData) {
                //Test that the filter category is the same.
                assertEquals("Popularity Ratings", returnToFilterCategoriesOutputData.getCategoryName());
                //Test that the list of selected options for the filter category the user is starting from is the same.
                assertEquals(categoryselections, returnToFilterCategoriesOutputData.getSelectedOptions());
                //Test that the list of filtered movies for the filter category the user is starting from is the same.
                assertEquals(filteredMovies, returnToFilterCategoriesOutputData.getFilteredMovies());
            }
        };
        ReturnToFilterCategoriesInputBoundary interactor = new ReturnToFilterCategoriesInteractor(
                returnToFilterCategoriesPresenter);
        interactor.execute(returnToFilterCategoriesInputData);
    }

}
