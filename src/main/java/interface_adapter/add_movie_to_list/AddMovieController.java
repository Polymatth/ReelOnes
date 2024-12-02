package interface_adapter.add_movie_to_list;

import entity.Movie;
import use_case.add_movie_to_list.AddMovieInputBoundary;
import use_case.add_movie_to_list.AddMovieInputData;

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
     * @param listName the name of the user list.
     * @param movieTitle the title of movie to add.
     */
    public void addMovieToList(String listName, String movieTitle) {
        AddMovieInputData inputData = new AddMovieInputData(listName, movieTitle);
        inputBoundary.addMovieToList(inputData);
    }
}
