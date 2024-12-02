package use_case.add_movie_to_list;
/**
 * Input Boundary interface for adding a movie to a user list.
 */
public interface AddMovieInputBoundary {
    /**
     * Adds a movie to a specified user list.
     * @param inputData the input data containing the list name and movie.
     */
    void addMovieToList(AddMovieInputData inputData);
}