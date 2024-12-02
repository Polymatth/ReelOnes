package use_case.add_movie_to_list;

/**
 * Interface for adding a movie to a user list.
 */
public interface AddMovieDataAccessInterface {
    /**
     * Adds a movie to a user list.
     * @param listId the ID of the user list
     * @param movieId the ID of the movie to add
     */
    void addMovieToList(String listId, String movieId);

}
