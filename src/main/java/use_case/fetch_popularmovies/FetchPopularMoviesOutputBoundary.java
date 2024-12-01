package use_case.fetch_popularmovies;

import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesOutputData;

public interface FetchPopularMoviesOutputBoundary {

    /**
     * Prepares the success view with the movie results.
     * @param outputData contains the list of found movies
     */
    void prepareSuccessView(FetchPopularMoviesOutputData outputData);

    /**
     * Prepares a failure view with an error message.
     * @param errorMessage a message describing the failure
     */
    void prepareFailView(String errorMessage);
}
