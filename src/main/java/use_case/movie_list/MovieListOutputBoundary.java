package use_case.movie_list;


/**
 * The output boundary for the MovieList Use Case.
 */
public interface MovieListOutputBoundary {
    /**
     * Prepares the success view for the Movie List Use Case.
     * @param movieListOutputData output data
     */
    void prepareSuccessView(MovieListOutputData movieListOutputData);
    void prepareFailView(String errorMessage);
}
