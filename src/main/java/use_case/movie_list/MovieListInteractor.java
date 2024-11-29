package use_case.movie_list;

import entity.Movie;
import entity.MovieList;
import entity.UserList;


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
    public List<Movie> getMoviesForList(String listName) {
        MovieList list = movieListDataAccessInterface.getMovieListByName(listName);
        if (list != null) {
            return list.getMovies();
        }
        return java.util.Collections.emptyList();
    }

    @Override
    public List<MovieList> getUserListsForUser(String userId) {
        return movieListDataAccessInterface.getUserListsForUser(userId);
    }


    @Override
    public void updateMovieList(MovieList movieList) {
        movieListDataAccessInterface.updateMovieList(movieList);
    }


    @Override
    public void addMovieToList(String listName, Movie movie) {
        movieListDataAccessInterface.addMovieToList(listName, movie);
    }

    @Override
    public void renameList(String listId, String newName) {
        movieListDataAccessInterface.renameList(listId, newName);
    }

    @Override
    public void deleteMovieFromList(String listName, Movie movie) {
        movieListDataAccessInterface.removeMovieFromList(listName, movie);
    }

    @Override
    public void deleteMovieList(String listName) {
        movieListDataAccessInterface.deleteMovieList(listName);
    }
}
