package use_case.return_to_list_from_movie_detail;

/**
 * The output boundary for the return to list from movie detail use case
 */
public interface ReturnToListFromMovieOutputBoundary {

    /**
     * Prepares the movie list view to return to from the movie detail view
     * @param returnToListFromMovieOutputData the return to list from movie detail output data
     */
    public void executeOriginView(ReturnToListFromMovieOutputData returnToListFromMovieOutputData);
}
