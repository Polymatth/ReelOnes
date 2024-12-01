package use_case.movie_detail;

import entity.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The movie detail interactor.
 */
public class MovieDetailInteractor implements MovieDetailInputBoundary {

    private MovieDetailOutputBoundary movieDetailPresenter;
    private MovieDetailDataAccessInterface dataAccessInterface;

    public MovieDetailInteractor(MovieDetailOutputBoundary movieDetailOutputBoundary,
                                 MovieDetailDataAccessInterface dataAccessInterface) {
        this.movieDetailPresenter = movieDetailOutputBoundary;
        this.dataAccessInterface = dataAccessInterface;
    }

    @Override
    public void execute(MovieDetailInputData movieDetailInputData) {
        Movie movie = movieDetailInputData.getMovie();
        String dir = dataAccessInterface.getDirector(movie.getID());
        movie.setDirector(dir);
        List<String> genres = dataAccessInterface.getGenres(movie.getGenre_ids());
        movie.setGenres(genres);
        List<String> streamingServices = dataAccessInterface.getStreamingServices(movie.getID());
        movie.setStreaming(streamingServices);
        final MovieDetailOutputData movieDetailOutputData = new MovieDetailOutputData(movie,
                movieDetailInputData.getOriginView());
        this.movieDetailPresenter.prepareSuccessView(movieDetailOutputData);
        //note: no prepareFailView because there is currently no way for this use case to fail.
    }

}
