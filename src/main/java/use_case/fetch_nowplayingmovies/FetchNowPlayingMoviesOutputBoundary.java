package use_case.fetch_nowplayingmovies;


import entity.Movie;

import java.util.List;

public interface FetchNowPlayingMoviesOutputBoundary {

    /**
     * Prepares the success view with the movie results.
     * @param outputData contains the list of found movies
     */
    void prepareSuccessView(FetchNowPlayingMoviesOutputData outputData);

    /**
     * Prepares a failure view with an error message.
     * @param errorMessage a message describing the failure
     */
    void prepareFailView(String errorMessage);
}
