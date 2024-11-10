package use_case.search_movie;

/**
 * Input data for the Search Movies Use Case.
 */
public class SearchMovieInputData {
    private final String searchQuery;

    public SearchMovieInputData(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getSearchQuery() {
        return searchQuery;
    }
}
