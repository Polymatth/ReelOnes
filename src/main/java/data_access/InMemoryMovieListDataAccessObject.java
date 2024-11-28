package data_access;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Movie;
import entity.MovieList;
import entity.UserList;
import use_case.movie_list.MovieListDataAccessInterface;

public class InMemoryMovieListDataAccessObject implements MovieListDataAccessInterface {

    private final Map<String, MovieList> movieListsByName = new HashMap<>();
    private final Map<String, List<MovieList>> movieListsByUserId = new HashMap<>();

    @Override
    public void createNewList(String userId, String listName, boolean isPublic) {
        MovieList newList = new UserList(userId, listName, isPublic);
        saveMovieList(newList);
    }

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
    public void deleteMovieList(String listName) {
        MovieList removedList = movieListsByName.remove(listName);
        if (removedList != null) {
            movieListsByUserId.getOrDefault(removedList.getUserId(), new java.util.ArrayList<>()).remove(removedList);
        }
    }

    @Override
    public void updateMovieList(MovieList movieList) {
        movieListsByName.put(movieList.getListName(), movieList);
    }

    @Override
    public void renameList(String listId, String newName) {
        MovieList movieList = movieListsByName.get(listId);
        if (movieList != null) {
            movieList.setListName(newName);  // Assuming MovieList has a method setListName()
            movieListsByName.put(newName, movieList);
            movieListsByName.remove(listId); // Remove the old list name entry
        } else {
            throw new RuntimeException("Movie list not found: " + listId);
        }
    }

    @Override
    public void addMovieToList(String listName, Movie movie) {
        MovieList movieList = movieListsByName.get(listName);
        if (movieList != null) {
            movieList.addMovie(movie);  // Assuming MovieList has a method addMovie()
            movieListsByName.put(listName, movieList);
        } else {
            throw new RuntimeException("Movie list not found: " + listName);
        }
    }

    @Override
    public void removeMovieFromList(String listName, Movie movie) {
        MovieList movieList = movieListsByName.get(listName);
        if (movieList != null) {
            movieList.removeMovie(movie);  // Assuming MovieList has a method removeMovie()
            movieListsByName.put(listName, movieList);
        } else {
            throw new RuntimeException("Movie list not found: " + listName);
        }
    }
}