package use_case.movie_list;

import entity.Movie;
import entity.MovieList;
import entity.UserList;
import use_case.movie_detail.MovieDetailInputData;
import use_case.movie_detail.MovieDetailOutputData;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieListInteractor implements MovieListInputBoundary {

    private final MovieListDataAccessInterface movieListDataAccessInterface;

    public MovieListInteractor(MovieListDataAccessInterface movieListDataAccessInterface) {
        this.movieListDataAccessInterface = movieListDataAccessInterface;
    }

    /**
     * Executes the action on the movie list, such as viewing or editing it.
     * @param movieListInputData The data for the movie list
     */
    @Override
    public void execute(MovieListInputData movieListInputData) {
        MovieList movieList = movieListInputData.getMovieList();
        System.out.println("Executing action on movie list: " + movieList.getListName());
    }

    @Override
    public void createNewList(String userId, String listName, Boolean isPublic) {
        MovieList movieList = new UserList(userId, listName, isPublic);
        MovieListInputData inputData = new MovieListInputData(movieList);
        movieListDataAccessInterface.saveMovieList(movieList);
        this.execute(inputData);
    }

    @Override
    public boolean movieListExists(String listName) {
        if (movieListDataAccessInterface.getMovieListByName(listName) == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<MovieList> getUserListsForUser(String userId) {
        return movieListDataAccessInterface.getUserListsForUser(userId);
    }

}
