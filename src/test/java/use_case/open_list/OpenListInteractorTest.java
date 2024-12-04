package use_case.open_list;

import entity.Movie;
import org.junit.jupiter.api.Test;
import use_case.open_list.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OpenListInteractorTest {

    @Test
    void successViewTest() {
        // Mock repository to return a predefined movie list
        OpenListDataAccessInterface repository = new OpenListDataAccessInterface() {
            @Override
            public List<Movie> getMoviesForList(String listName) {
                List<Movie> movies = new ArrayList<>();
                movies.add(new Movie("/path1.jpg", false, "Overview 1", "2024-11-01",
                        List.of(28, 12), 101, "en", "Movie 1", "/backdrop1.jpg",
                        8.5f, 1500, false, 7.9f));
                movies.add(new Movie("/path2.jpg", false, "Overview 2", "2023-12-10",
                        List.of(16, 35), 102, "en", "Movie 2", "/backdrop2.jpg",
                        7.3f, 1200, false, 8.1f));
                return movies;
            }
        };

        // Mock presenter to validate success view
        OpenListOutputBoundary presenter = new OpenListOutputBoundary() {
            @Override
            public void prepareSuccessView(OpenListOutputData outputData) {
                assertEquals("MyList", outputData.getListName());
                assertEquals(2, outputData.getMovies().size());
                assertEquals("Movie 1", outputData.getMovies().get(0).getTitle());
                assertEquals("Movie 2", outputData.getMovies().get(1).getTitle());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Error view was unexpectedly prepared: " + error);
            }
        };

        OpenListInputBoundary interactor = new OpenListInteractor(repository, presenter);
        OpenListInputData inputData = new OpenListInputData("MyList");

        // Execute the use case
        interactor.execute(inputData);
    }

    @Test
    void failViewTest() {
        // Mock repository to throw an exception
        OpenListDataAccessInterface repository = new OpenListDataAccessInterface() {
            @Override
            public List<Movie> getMoviesForList(String listName) {
                throw new RuntimeException("Test exception");
            }
        };

        // Mock presenter to validate failure view
        OpenListOutputBoundary presenter = new OpenListOutputBoundary() {
            @Override
            public void prepareSuccessView(OpenListOutputData outputData) {
                fail("Success view was unexpectedly prepared.");
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(error.contains("Error opening list: Test exception"));
            }
        };

        OpenListInputBoundary interactor = new OpenListInteractor(repository, presenter);
        OpenListInputData inputData = new OpenListInputData("MyList");

        // Execute the use case
        interactor.execute(inputData);
    }
}