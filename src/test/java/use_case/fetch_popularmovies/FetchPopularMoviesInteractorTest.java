package use_case.fetch_popularmovies;

import app.AppConfig;
import entity.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FetchPopularMoviesInteractorTest {

    @Test
    void fetchPopularMoviesSuccess() {
        // Set up configuration and data access interface
        AppConfig config = new AppConfig();
        FetchPopularMoviesDataAccessInterface dataAccessInterface = config.getPopularMoviesDataAccess();

        // Create sample movie data for testing
        String path = "/pathToImage.jpg";
        List<Integer> genreIds = new ArrayList<>();
        genreIds.add(28);  // Action
        genreIds.add(12);  // Adventure
        int id = 12345;
        Movie testMovie = new Movie(path, false, "A thrilling action-adventure movie.", "2024-11-30", genreIds, id,
                "en", "Popular Movie", "/backdropPath.jpg", 8.0f, 100, false, 4.2f);

        // Prepare test data access behavior (mocking)
        List<Movie> testMovies = new ArrayList<>();
        testMovies.add(testMovie);
        FetchPopularMoviesDataAccessInterface mockDataAccess = new FetchPopularMoviesDataAccessInterface() {
            @Override
            public List<Movie> getPopularMovies() {
                return testMovies;
            }
        };

        // Create a test presenter that checks the result
        FetchPopularMoviesOutputBoundary presenter = new FetchPopularMoviesOutputBoundary() {
            @Override
            public void prepareSuccessView(FetchPopularMoviesOutputData outputData) {
                // Test that the correct movie data is returned
                assertEquals(1, outputData.getMovies().size());
                assertEquals("Popular Movie", outputData.getMovies().get(0).getTitle());

            }

            @Override
            public void prepareFailView(String error) {
                // In case of failure, fail the test
                assertEquals("Failed to retrieve movies.", error);
            }
        };

        // Create the interactor and call fetchPopularMovies()
        FetchPopularMoviesInputBoundary interactor = new FetchPopularMoviesInteractor(presenter, mockDataAccess);
        interactor.fetchPopularMovies();
    }


    @Test
    void fetchPopularMoviesFailure() {
        // Prepare a mock data access that throws an exception
        FetchPopularMoviesDataAccessInterface mockDataAccess = new FetchPopularMoviesDataAccessInterface() {
            @Override
            public List<Movie> getPopularMovies() {
                throw new RuntimeException("Error fetching popular movies");
            }
        };

        // Create a test presenter to check the failure case
        FetchPopularMoviesOutputBoundary presenter = new FetchPopularMoviesOutputBoundary() {
            @Override
            public void prepareSuccessView(FetchPopularMoviesOutputData outputData) {
                // Fail the test if the success view is called
                assert false : "Expected failure but got success.";
            }

            @Override
            public void prepareFailView(String error) {
                // Verify that failure message is handled correctly
                assertEquals("Failed to retrieve movies.", error);
            }
        };

        // Create the interactor and call fetchPopularMovies()
        FetchPopularMoviesInputBoundary interactor = new FetchPopularMoviesInteractor(presenter, mockDataAccess);
        interactor.fetchPopularMovies();
    }
}