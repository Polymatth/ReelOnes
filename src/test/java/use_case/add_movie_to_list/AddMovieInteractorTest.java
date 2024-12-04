package use_case.add_movie_to_list;

import data_access.InMemoryAddMovieDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddMovieInteractorTest {

    @Test
    void successAddMovieTest() {
        // Create the in-memory DAO implementation
        InMemoryAddMovieDataAccessObject dataAccess = new InMemoryAddMovieDataAccessObject();

        // Mock output boundary to validate success view
        AddMovieOutputBoundary outputBoundary = new AddMovieOutputBoundary() {
            @Override
            public void prepareSuccessView(AddMovieOutputData outputData) {
                assertEquals("MyList", outputData.getListName());
                assertEquals("Movie 1", outputData.getMovieTitle());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Failure view was unexpectedly prepared: " + error);
            }
        };

        // Mock user factory to create a user with a pre-existing list
        UserFactory userFactory = (username, password, favMovie, favDirector, movieListsList) -> {
            List<MovieList> movieLists = new ArrayList<>();
            MovieList list = new UserList(username, "MyList", false, new ArrayList<>());
            movieLists.add(list);
            return new CommonUser(username, password, favMovie, favDirector, movieLists);
        };

        AddMovieInputBoundary interactor = new AddMovieInteractor(dataAccess, outputBoundary, userFactory);

        List<MovieList> movieLists = new ArrayList<>();
        movieLists.add(new UserList("testUser", "MyList", false, new ArrayList<>()));

        AddMovieInputData inputData = new AddMovieInputData(
                "testUser",
                "password123",
                "Inception",
                "Christopher Nolan",
                movieLists,
                "MyList",
                "Movie 1"
        );

        // Execute the use case
        interactor.addMovieToList(inputData);

        // Verify the movie was added correctly in the in-memory database
        User user = dataAccess.getUserByUsername("testUser");
        MovieList movieList = dataAccess.findMovieListByUser("testUser", "MyList");

        assertNotNull(user);
        for (Movie movie : movieList.getMovies()) {
            assertTrue(movie.getTitle().equals("Movie 1"));
        }
    }

    @Test
    void failEmptyListNameTest() {
        AddMovieDataAccessInterface dataAccess = user -> fail("saveMovieList should not be called for invalid input.");

        AddMovieOutputBoundary outputBoundary = new AddMovieOutputBoundary() {
            @Override
            public void prepareSuccessView(AddMovieOutputData outputData) {
                fail("Success view was unexpectedly prepared.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("List name cannot be empty.", error);
            }
        };

        UserFactory userFactory = (username, password, favMovie, favDirector, movieListsList) -> null;

        AddMovieInputBoundary interactor = new AddMovieInteractor(dataAccess, outputBoundary, userFactory);

        List<MovieList> movieLists = new ArrayList<>();
        movieLists.add(new UserList("testUser", "MyList", false, new ArrayList<>()));

        AddMovieInputData inputData = new AddMovieInputData(
                "testUser",
                "password123",
                "Inception",
                "Christopher Nolan",
                movieLists,
                "",
                "Movie 1"
        );

        interactor.addMovieToList(inputData);
    }

    @Test
    void failEmptyMovieTitleTest() {
        AddMovieDataAccessInterface dataAccess = user -> fail("saveMovieList should not be called for invalid input.");

        AddMovieOutputBoundary outputBoundary = new AddMovieOutputBoundary() {
            @Override
            public void prepareSuccessView(AddMovieOutputData outputData) {
                fail("Success view was unexpectedly prepared.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid movie data. Movie cannot be null or missing an ID.", error);
            }
        };

        UserFactory userFactory = (username, password, favMovie, favDirector, movieListsList) -> null;

        AddMovieInputBoundary interactor = new AddMovieInteractor(dataAccess, outputBoundary, userFactory);

        List<MovieList> movieLists = new ArrayList<>();
        movieLists.add(new UserList("testUser", "MyList", false, new ArrayList<>()));

        AddMovieInputData inputData = new AddMovieInputData(
                "testUser",
                "password123",
                "Inception",
                "Christopher Nolan",
                movieLists,
                "MyList",
                ""
        );

        interactor.addMovieToList(inputData);
    }

    @Test
    void failSaveMovieListExceptionTest() {
        AddMovieDataAccessInterface dataAccess = user -> {
            throw new RuntimeException("Database error");
        };

        AddMovieOutputBoundary outputBoundary = new AddMovieOutputBoundary() {
            @Override
            public void prepareSuccessView(AddMovieOutputData outputData) {
                fail("Success view was unexpectedly prepared.");
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(error.contains("Failed to add movie to list: Database error"));
            }
        };

        UserFactory userFactory = (username, password, favMovie, favDirector, movieListsList) -> {
            List<MovieList> movieLists = new ArrayList<>();
            movieLists.add(new UserList(username, "MyList", false, new ArrayList<>()));
            return new CommonUser(username, password, favMovie, favDirector, movieLists);
        };

        AddMovieInputBoundary interactor = new AddMovieInteractor(dataAccess, outputBoundary, userFactory);

        List<MovieList> movieLists = new ArrayList<>();
        movieLists.add(new UserList("testUser", "MyList", false, new ArrayList<>()));

        AddMovieInputData inputData = new AddMovieInputData(
                "testUser",
                "password123",
                "Inception",
                "Christopher Nolan",
                movieLists,
                "MyList",
                "Movie 1"
        );

        interactor.addMovieToList(inputData);
    }
}