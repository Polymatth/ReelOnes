package use_case.search_movie;

import entity.Movie;
import java.util.List;

/**
 * Interactor for the Search Movies Use Case
 */
public class SearchMovieInteractor implements SearchMovieInputBoundary {
    private final SearchMovieOutputBoundary presenter;
    private final SearchMovieDataAccessInterface dataAccessInterface;

    public SearchMovieInteractor(SearchMovieOutputBoundary presenter, SearchMovieDataAccessInterface dataAccessInterface) {
        this.presenter = presenter;
        this.dataAccessInterface = dataAccessInterface;
    }

    @Override
    public void search(SearchMovieInputData inputData) {
        try {
            List<Movie> movies = dataAccessInterface.searchMoviesByQuery(inputData.getSearchQuery());
            SearchMovieOutputData outputData = new SearchMovieOutputData(movies);
            presenter.prepareSuccessView(outputData);
        } catch (Exception e) {
            presenter.prepareFailView("Failed to retrieve movies.");
        }
    }
}
