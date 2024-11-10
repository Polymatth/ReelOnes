package interface_adapter.search_movie;

import use_case.search_movie.SearchMovieInputBoundary;
import use_case.search_movie.SearchMovieInputData;

/**
 * Controller for the Search Movies Use Case
 */
public class SearchMoviesController {
    private final SearchMovieInputBoundary inputBoundary;

    public SearchMoviesController(SearchMovieInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Executes the Search Movies Use Case
     * @param searchQuery the search term entered by the user
     */
    public void executeSearch(String searchQuery) {
        SearchMovieInputData inputData = new SearchMovieInputData(searchQuery);
        inputBoundary.search(inputData);
    }
}
