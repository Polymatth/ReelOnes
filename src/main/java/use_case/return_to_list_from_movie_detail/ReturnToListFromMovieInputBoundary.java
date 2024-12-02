package use_case.return_to_list_from_movie_detail;

/**
 * The input boundary for the return to list from movie detail view
 */
public interface ReturnToListFromMovieInputBoundary {

    /**
     * Executes the return to list from movie detail view use case
     * @param returnToListFromMovieInputData the return to list from movie detail input data
     */
    public void execute(ReturnToListFromMovieInputData returnToListFromMovieInputData);
}
