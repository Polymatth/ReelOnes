package interface_adapter.movie_list;

import entity.Movie;
import entity.MovieList;
import entity.UserList;
import use_case.movie_list.MovieListInputBoundary;
import use_case.movie_list.MovieListInputData;
import use_case.movie_list.MovieListInteractor;

import java.util.List;

public class MovieListController {
    private final MovieListInputBoundary movieListUseCaseInteractor;

    public MovieListController(MovieListInputBoundary movieListUseCaseInteractor) {
        this.movieListUseCaseInteractor = movieListUseCaseInteractor;
    }

    /**
     * Executes the Movie List Selection Use Case.
     *
     * @param movieList the movie list selected
     */
    public void execute(MovieList movieList) {
        final MovieListInputData movieListInputData = new MovieListInputData(movieList);
        movieListUseCaseInteractor.execute(movieListInputData);
    }

}