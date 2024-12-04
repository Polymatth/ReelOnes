package data_access;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Movie;
import entity.MovieList;
import entity.User;
import entity.UserList;
import use_case.movie_list.MovieListDataAccessInterface;

// temporary class. will delete it after implementing DBMovieListDataAccessObject.
public class InMemoryMovieListDataAccessObject implements MovieListDataAccessInterface {

    private final Map<String, MovieList> movieListsByName = new HashMap<>();
    private final Map<String, List<MovieList>> movieListsByUserId = new HashMap<>();

    @Override
    public void saveMovieList(User user) {
//        movieListsByName.put(movieList.getListName(), movieList);
//        movieListsByUserId
//                .computeIfAbsent(movieList.getUserId(), k -> new java.util.ArrayList<>())
//                .add(movieList);
    }

    @Override
    public MovieList getMovieListByName(String listName, String username) {
        return movieListsByName.get(listName);
    }

    @Override
    public List<MovieList> getUserListsForUser(String userId) {
        return movieListsByUserId.getOrDefault(userId, new java.util.ArrayList<>());
    }

}