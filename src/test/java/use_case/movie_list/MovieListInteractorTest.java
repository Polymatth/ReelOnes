package use_case.movie_list;

import entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class MovieListInteractorTest {

    @Test
    void executeCreateMovieListTest() {
        // Mock the Data Access Interface
        MovieListDataAccessInterface movieListDataAccess = new MovieListDataAccessInterface() {
            @Override
            public void saveMovieList(User user) {
                assertNotNull(user);
                assertEquals("testUser", user.getName());
                assertEquals(1, user.getMovieLists().size());
                assertEquals("MyMovieList", user.getMovieLists().get(0).getListName());
            }

            @Override
            public MovieList getMovieListByName(String listName, String username) {
                return null; // No need for this method in this test
            }

            @Override
            public List<MovieList> getUserListsForUser(String userId) {
                return null; // No need for this method in this test
            }
        };

        // Mock the Output Boundary (Presenter)
        MovieListOutputBoundary movieListPresenter = new MovieListOutputBoundary() {
            @Override
            public void prepareSuccessView(MovieListOutputData outputData) {
                assertNotNull(outputData);
                assertEquals(1, outputData.getMovieList().size());
                assertEquals("MyMovieList", outputData.getMovieList().get(0).getListName());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Failure view was unexpectedly prepared: " + error);
            }
        };

        // Mock the User Factory
        UserFactory userFactory = (username, password, favMovie, favDirector, movieLists) -> {
            return new CommonUser(username, password, favMovie, favDirector, movieLists);
        };

        // Create an instance of the Interactor
        MovieListInteractor interactor = new MovieListInteractor(movieListDataAccess, movieListPresenter, userFactory);

        // Create input data
        List<MovieList> movieLists = new ArrayList<>();
        MovieList movieList = new UserList("testUser", "MyMovieList", false, new ArrayList<>());
        movieLists.add(movieList);

        MovieListInputData inputData = new MovieListInputData("testUser", "password123", "Inception", "Christopher Nolan", movieLists);

        // Execute the use case
        interactor.execute(inputData);
    }

    @Test
    void movieListExistsTest() {
        // Mock the Data Access Interface
        MovieListDataAccessInterface movieListDataAccess = new MovieListDataAccessInterface() {
            @Override
            public void saveMovieList(User user) {
                // No need for this method in this test
            }

            @Override
            public MovieList getMovieListByName(String listName, String username) {
                if ("testUser".equals(username) && "MyMovieList".equals(listName)) {
                    return new UserList("testUser", "MyMovieList", false, new ArrayList<>());
                }
                return null;
            }

            @Override
            public List<MovieList> getUserListsForUser(String userId) {
                return null; // No need for this method in this test
            }
        };

        // Mock the Output Boundary (Presenter)
        MovieListOutputBoundary movieListPresenter = new MovieListOutputBoundary() {
            @Override
            public void prepareSuccessView(MovieListOutputData outputData) {
                fail("Success view should not be called in this test");
            }

            @Override
            public void prepareFailView(String error) {
                // Not needed for this test
            }
        };

        // Mock the User Factory
        UserFactory userFactory = (username, password, favMovie, favDirector, movieLists) -> {
            return new CommonUser(username, password, favMovie, favDirector, movieLists);
        };

        // Create an instance of the Interactor
        MovieListInteractor interactor = new MovieListInteractor(movieListDataAccess, movieListPresenter, userFactory);

        // Test that the movie list exists
        assertTrue(interactor.movieListExists("MyMovieList", "testUser"));

        // Test that the movie list does not exist
        assertFalse(interactor.movieListExists("NonExistentList", "testUser"));
    }

    @Test
    void getUserListsForUserTest() {
        // Mock the Data Access Interface
        MovieListDataAccessInterface movieListDataAccess = new MovieListDataAccessInterface() {
            @Override
            public void saveMovieList(User user) {
                // No need for this method in this test
            }

            @Override
            public MovieList getMovieListByName(String listName, String username) {
                return null; // Not needed for this test
            }

            @Override
            public List<MovieList> getUserListsForUser(String userId) {
                if ("testUser".equals(userId)) {
                    List<MovieList> movieLists = new ArrayList<>();
                    MovieList movieList = new UserList("testUser", "MyMovieList", false, new ArrayList<>());
                    movieLists.add(movieList);
                    return movieLists;
                }
                return new ArrayList<>();
            }
        };

        // Mock the Output Boundary (Presenter)
        MovieListOutputBoundary movieListPresenter = new MovieListOutputBoundary() {
            @Override
            public void prepareSuccessView(MovieListOutputData outputData) {
                assertNotNull(outputData);
                assertEquals(1, outputData.getMovieList().size());
                assertEquals("MyMovieList", outputData.getMovieList().get(0).getListName());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Failure view was unexpectedly prepared: " + error);
            }
        };

        // Mock the User Factory
        UserFactory userFactory = (username, password, favMovie, favDirector, movieLists) -> {
            return new CommonUser(username, password, favMovie, favDirector, movieLists);
        };

        // Create an instance of the Interactor
        MovieListInteractor interactor = new MovieListInteractor(movieListDataAccess, movieListPresenter, userFactory);

        // Retrieve and verify the user's movie lists
        List<MovieList> userLists = interactor.getUserListsForUser("testUser");
        assertNotNull(userLists);
        assertEquals(1, userLists.size());
        assertEquals("MyMovieList", userLists.get(0).getListName());
    }
}