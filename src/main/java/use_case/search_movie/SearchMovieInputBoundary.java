package use_case.search_movie;

public interface SearchMovieInputBoundary {

    /**
     * Executes the Search Movies Use Case.
     * @param inputData the input data containing the search query
     */
    void search(SearchMovieInputData inputData);
}
