package interface_adapter.movie_list;

import entity.Movie;
import entity.MovieList;
import entity.UserList;
import use_case.movie_list.MovieListInputBoundary;
import use_case.movie_list.MovieListInputData;
import use_case.movie_list.MovieListInteractor;

import java.util.List;

/**
 * Controller for handling requests to create a user list.
 */
public class MovieListController {
    private final MovieListInputBoundary movieListUseCaseInteractor;

    public MovieListController(MovieListInputBoundary movieListUseCaseInteractor) {
        this.movieListUseCaseInteractor = movieListUseCaseInteractor;
    }

    /**
     * Executes the Movie List Selection Use Case.
     * @param movieListList the movie list selected
     */
    public void execute(String username, String password, String favMovie, String favDirector, List<MovieList> movieListList) {
        final MovieListInputData movieListInputData = new MovieListInputData(username, password, favMovie, favDirector, movieListList);

        movieListUseCaseInteractor.execute(movieListInputData);
    }

}