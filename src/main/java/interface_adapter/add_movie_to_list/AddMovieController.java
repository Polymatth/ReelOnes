package interface_adapter.add_movie_to_list;

import entity.MovieList;
import use_case.add_movie_to_list.AddMovieInputBoundary;
import use_case.add_movie_to_list.AddMovieInputData;

import java.util.List;

/**
 * Controller for handling requests to add a movie to a user list.
 */
public class AddMovieController {
    private final AddMovieInputBoundary inputBoundary;

    /**
     * Constructs an AddMovieToListController object.
     *
     * @param inputBoundary the input boundary for adding movies to lists.
     */
    public AddMovieController(AddMovieInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Adds a movie to a specified user list.
     *
     *
     */
    public void addMovieToList(String username, String password, String favMovie, String favDirector, List<MovieList> movieListLists,String listName, String movieTitle) {
        AddMovieInputData inputData = new AddMovieInputData(username,password,favMovie,favDirector,movieListLists, listName,movieTitle);
        inputBoundary.addMovieToList(inputData);
    }
}
