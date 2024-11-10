package interface_adapter.movie_detail_page;

import entity.Movie;
import use_case.movie_detail.MovieDetailInputBoundary;
import use_case.movie_detail.MovieDetailInputData;


/**
 * Controller for the Movie Detail Selection Use Case.
 */
public class MovieDetailController {
    private final MovieDetailInputBoundary movieDetailUseCaseInteractor;

    public MovieDetailController(MovieDetailInputBoundary movieDetailUseCaseInteractor) {
        this.movieDetailUseCaseInteractor = movieDetailUseCaseInteractor;
    }

    /**
     * Executes the Movie Detail Selection Use Case.
     * @param movie the movie selected
     */
    public void execute(Movie movie) {
        final MovieDetailInputData movieDetailInputData = new MovieDetailInputData(movie);

        movieDetailUseCaseInteractor.execute(movieDetailInputData);
    }
}
