package use_case.add_movie_to_list;

/**
 * Output Boundary interface for presenting the result of adding a movie to a user list.
 */
public interface AddMovieOutputBoundary {
    /**
     * Prepares the success view with output data.
     * @param outputData the output data containing the result.
     */
    void prepareSuccessView(AddMovieOutputData outputData);

    /**
     * Prepares the fail view with an error message.
     * @param errorMessage the error message to present.
     */
    void prepareFailView(String errorMessage);
}
