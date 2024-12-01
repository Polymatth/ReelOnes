package interface_adapter.fetch_popularmovies;

import use_case.fetch_popularmovies.FetchPopularMoviesInputBoundary;

public class FetchPopularMoviesController {
    private final FetchPopularMoviesInputBoundary fetchPopularMoviesInputBoundary;

    public FetchPopularMoviesController(FetchPopularMoviesInputBoundary fetchPopularMoviesInputBoundary) {
        this.fetchPopularMoviesInputBoundary = fetchPopularMoviesInputBoundary;
    }

    /**
     * Executes the Fetch Popular Movies Use Case
     */
    public void execute(){
        fetchPopularMoviesInputBoundary.fetchPopularMovies();
    }
}
