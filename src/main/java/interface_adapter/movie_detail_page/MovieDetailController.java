package interface_adapter.change_password;

import entity.Movie;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;
import use_case.movie_detail.MovieDetailInputBoundary;
import use_case.movie_detail.MovieDetailInputData;

import java.util.Map;

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
        Map<Integer, String> genreNames = MovieAPIAccess.get
        final MovieDetailInputData movieDetailInputData = new MovieDetailInputData(movie);

        movieDetailUseCaseInteractor.execute(movieDetailInputData);
    }
}
