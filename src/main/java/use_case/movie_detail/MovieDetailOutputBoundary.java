package use_case.movie_detail;

/**
 * The output boundary for the movie detail selection use case
 */
public interface MovieDetailOutputBoundary {

    /**
     * Prepares the success view for the Movie Detail Selection Use Case.
     * @param movieDetailOutputData output data
     */
    void prepareSuccessView(MovieDetailOutputData movieDetailOutputData);

}
