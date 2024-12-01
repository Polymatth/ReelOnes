package interface_adapter.movie_detail_page;

import entity.Movie;
import use_case.movie_detail.MovieDetailInputBoundary;
import use_case.movie_detail.MovieDetailInputData;
import use_case.return_to_list_from_movie_detail.ReturnToListFromMovieInputBoundary;
import use_case.return_to_list_from_movie_detail.ReturnToListFromMovieInputData;


/**
 * Controller for the Movie Detail Selection Use Case.
 */
public class MovieDetailController {
    private final MovieDetailInputBoundary movieDetailUseCaseInteractor;
    private final ReturnToListFromMovieInputBoundary returnToListFromMovieInteractor;

    public MovieDetailController(MovieDetailInputBoundary movieDetailUseCaseInteractor,
                                 ReturnToListFromMovieInputBoundary returnToListFromMovieInteractor) {
        this.movieDetailUseCaseInteractor = movieDetailUseCaseInteractor;
        this.returnToListFromMovieInteractor = returnToListFromMovieInteractor;
    }

    /**
     * Executes the Movie Detail Selection Use Case.
     * @param movie the movie selected
     * @param originView the view name from the list from which the user selected a movie.
     */
    public void execute(Movie movie, String originView) {
        final MovieDetailInputData movieDetailInputData = new MovieDetailInputData(movie, originView);

        movieDetailUseCaseInteractor.execute(movieDetailInputData);
    }

    /**
     * Executes the Return to List from Movie Detail view use case
     * @param originView the list view that the user wants to return to
     */
    public void executeReturnToListView(String originView) {
        final ReturnToListFromMovieInputData returnToListFromMovieInputData = new
                ReturnToListFromMovieInputData(originView);
        returnToListFromMovieInteractor.execute(returnToListFromMovieInputData);
    }
}
