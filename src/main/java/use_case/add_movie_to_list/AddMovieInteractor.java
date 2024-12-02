package use_case.add_movie_to_list;

import entity.Movie;

/**
 * Interactor for handling the addition of a movie to a user list.
 * Implements the AddMovieInputBoundary interface.
 */
public class AddMovieInteractor implements AddMovieInputBoundary {
    private final AddMovieDataAccessInterface dataAccess;
    private final AddMovieOutputBoundary outputBoundary;

    public AddMovieInteractor(AddMovieDataAccessInterface dataAccess, AddMovieOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void addMovieToList(AddMovieInputData inputData) {
        String listName = inputData.getListName();
        String movieTitle = inputData.getMovieTitle();

        if (listName == null || listName.isEmpty()) {
            outputBoundary.prepareFailView("List name cannot be empty.");
            return;
        }

        if (movieTitle == null || movieTitle.isEmpty()) {
            outputBoundary.prepareFailView("Invalid movie data. Movie cannot be null or missing an ID.");
            return;
        }

        try {
            // Add the movie to the list using the data access layer
            dataAccess.addMovieToList(listName, movieTitle);

            // Prepare the success view with the output data
            AddMovieOutputData outputData = new AddMovieOutputData(listName, movieTitle);
            outputBoundary.prepareSuccessView(outputData);
        } catch (Exception e) {
            // Handle any exceptions that may occur during the operation
            outputBoundary.prepareFailView("Failed to add movie to list: " + e.getMessage());
        }
    }
}