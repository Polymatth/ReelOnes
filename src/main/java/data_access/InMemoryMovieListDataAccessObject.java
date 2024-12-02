package data_access;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Movie;
import entity.MovieList;
import entity.UserList;
import use_case.edit_list.EditListDataAccessInterface;
import use_case.movie_list.MovieListDataAccessInterface;

// temporary class. will delete it after implementing DBMovieListDataAccessObject.
public class InMemoryMovieListDataAccessObject implements MovieListDataAccessInterface, EditListDataAccessInterface {

    private final Map<String, MovieList> movieListsByName = new HashMap<>();
    private final Map<String, List<MovieList>> movieListsByUserId = new HashMap<>();

    @Override
    public void saveMovieList(MovieList movieList) {
        movieListsByName.put(movieList.getListName(), movieList);
        movieListsByUserId
                .computeIfAbsent(movieList.getUserId(), k -> new java.util.ArrayList<>())
                .add(movieList);
    }

    @Override
    public MovieList getMovieListByName(String listName) {
        return movieListsByName.get(listName);
    }

    @Override
    public List<MovieList> getUserListsForUser(String userId) {
        return movieListsByUserId.getOrDefault(userId, new java.util.ArrayList<>());
    }

    @Override
    public MovieList getList(String username, String listName) {
        return this.movieListsByName.get(username);

    }

    @Override
    public void updateList(String username, MovieList movieList) {
    // implement later
    }
}