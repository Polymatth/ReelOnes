package use_case.search_movie;

public interface SearchMovieOutputBoundary {

    /**
     * Prepares the success view with the search results.
     * @param outputData contains the list of found movies
     */
    void prepareSuccessView(SearchMovieOutputData outputData);

    /**
     * Prepares a failure view with an error message.
     * @param errorMessage a message describing the failure
     */
    void prepareFailView(String errorMessage);
}
