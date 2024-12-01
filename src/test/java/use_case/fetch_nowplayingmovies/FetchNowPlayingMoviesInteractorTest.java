package use_case.fetch_nowplayingmovies;

import app.AppConfig;
import entity.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FetchNowPlayingMoviesInteractorTest {

    @Test
    void fetchNowPlayingMoviesSuccess() {
        // Set up configuration and data access interface
        AppConfig config = new AppConfig();
        FetchNowPlayingMoviesDataAccessInterface dataAccessInterface = config. getNowPlayingMovieDataAccess();

        // Create sample movie data for testing
        String path = "/pathToImage.jpg";
        List<Integer> genreIds = new ArrayList<>();
        genreIds.add(28);  // Action
        genreIds.add(12);  // Adventure
        int id = 12345;
        Movie testMovie = new Movie(path, false, "An exciting action-adventure film.", "2024-11-30", genreIds, id,
                "en", "Exciting Movie", "/backdropPath.jpg", 8.5f, 120, false, 4.5f);

        // Prepare test data access behavior (mocking)
        List<Movie> testMovies = new ArrayList<>();
        testMovies.add(testMovie);
        FetchNowPlayingMoviesDataAccessInterface mockDataAccess = new FetchNowPlayingMoviesDataAccessInterface() {
            @Override
            public List<Movie> getNowPlayingMovies() {
                return testMovies;
            }
        };

        // Create a test presenter that checks the result
        FetchNowPlayingMoviesOutputBoundary presenter = new FetchNowPlayingMoviesOutputBoundary() {
            @Override
            public void prepareSuccessView(FetchNowPlayingMoviesOutputData outputData) {
                // Test that the correct movie data is returned
                assertEquals(1, outputData.getMovies().size());
                assertEquals("Exciting Movie", outputData.getMovies().get(0).getTitle());
            }

            @Override
            public void prepareFailView(String error) {
                // In case of failure, fail the test
                assertEquals("Failed to retrieve movies.", error);
            }
        };

        // Create the interactor and call fetchNowPlayingMovies()
        FetchNowPlayingMoviesInputBoundary interactor = new FetchNowPlayingMoviesInteractor(presenter, mockDataAccess);
        interactor.fetchNowPlayingMovies();
    }

    @Test
    void fetchNowPlayingMoviesFailure() {
        // Prepare a mock data access that throws an exception
        FetchNowPlayingMoviesDataAccessInterface mockDataAccess = new FetchNowPlayingMoviesDataAccessInterface() {
            @Override
            public List<Movie> getNowPlayingMovies() {
                throw new RuntimeException("Error fetching movies");
            }
        };

        // Create a test presenter to check the failure case
        FetchNowPlayingMoviesOutputBoundary presenter = new FetchNowPlayingMoviesOutputBoundary() {
            @Override
            public void prepareSuccessView(FetchNowPlayingMoviesOutputData outputData) {
                // Fail the test if the success view is called
                assert false : "Expected failure but got success.";
            }

            @Override
            public void prepareFailView(String error) {
                // Verify that failure message is handled correctly
                assertEquals("Failed to retrieve movies.", error);
            }
        };

        // Create the interactor and call fetchNowPlayingMovies()
        FetchNowPlayingMoviesInputBoundary interactor = new FetchNowPlayingMoviesInteractor(presenter, mockDataAccess);
        interactor.fetchNowPlayingMovies();
    }
}

