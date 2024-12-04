package use_case.movie_list;

import entity.*;
import interface_adapter.movie_list.MovieListPresenter;
import use_case.movie_detail.MovieDetailInputData;
import use_case.movie_detail.MovieDetailOutputData;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The MovieList Interactor.
 */
public class MovieListInteractor implements MovieListInputBoundary {

    private final MovieListDataAccessInterface movieListDataAccessInterface;
    private final MovieListOutputBoundary movieListPresenter;
    private final UserFactory userFactory;

    public MovieListInteractor(MovieListDataAccessInterface movieListDataAccessInterface, MovieListOutputBoundary movieListPresenter, UserFactory userFactory) {
        this.movieListDataAccessInterface = movieListDataAccessInterface;
        this.movieListPresenter = movieListPresenter;
        this.userFactory = userFactory;
    }

    /**
     * Executes the action of creating a movie list.
     * @param movieListInputData The data for the movie list
     */
    @Override
    public void execute(MovieListInputData movieListInputData) {
        final User user = userFactory.create(movieListInputData.getUsername(),movieListInputData.getPassword(), movieListInputData.getFavMovie(),movieListInputData.getFavDirector(),movieListInputData.getMovieListList());
        movieListDataAccessInterface.saveMovieList(user);

        final MovieListOutputData movieListOutputData = new MovieListOutputData(user.getMovieLists());
        this.movieListPresenter.prepareSuccessView(movieListOutputData);
    }



    @Override
    public boolean movieListExists(String listName, String username) {
        if (movieListDataAccessInterface.getMovieListByName(listName, username) == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<MovieList> getUserListsForUser(String userId) {
        return movieListDataAccessInterface.getUserListsForUser(userId);
    }

}
