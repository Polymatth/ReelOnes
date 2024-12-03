package use_case.add_movie_to_list;

import entity.Movie;
import entity.User;
import entity.UserFactory;

/**
 * Interactor for handling the addition of a movie to a user list.
 * Implements the AddMovieInputBoundary interface.
 */
public class AddMovieInteractor implements AddMovieInputBoundary {
    private final AddMovieDataAccessInterface dataAccess;
    private final AddMovieOutputBoundary outputBoundary;
    private final UserFactory userFactory;


    public AddMovieInteractor(AddMovieDataAccessInterface dataAccess, AddMovieOutputBoundary outputBoundary,UserFactory userFactory) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void addMovieToList(AddMovieInputData inputData) {

        String listName = inputData.getListName();
        String movieTitle = inputData.getMovieTitle();
        String username = inputData.getUsername();

        if (listName == null || listName.isEmpty()) {
            outputBoundary.prepareFailView("List name cannot be empty.");
            return;
        }

        if (movieTitle == null || movieTitle.isEmpty()) {
            outputBoundary.prepareFailView("Invalid movie data. Movie cannot be null or missing an ID.");
            return;
        }

        try {
            final User user = userFactory.create(inputData.getUsername(),inputData.getPassword(), inputData.getFavMovie(),inputData.getFavDirector(),inputData.getMovieListsList());
            // Add the movie to the list using the data access layer
            dataAccess.saveMovieList(user);

            // Prepare the success view with the output data
            AddMovieOutputData outputData = new AddMovieOutputData(listName, movieTitle);
            outputBoundary.prepareSuccessView(outputData);
        } catch (Exception e) {
            // Handle any exceptions that may occur during the operation
            outputBoundary.prepareFailView("Failed to add movie to list: " + e.getMessage());
        }
    }
}