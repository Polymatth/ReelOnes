package use_case.movie_list;

public interface MovieListOutputBoundary {
    /**
     * Prepares the success view for the Movie List Use Case.
     * @param movieListOutputData output data
     */
    void prepareSuccessView(MovieListOutputData movieListOutputData);
    void prepareFailView(String errorMessage);
}
