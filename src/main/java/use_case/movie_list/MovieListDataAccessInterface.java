package use_case.movie_list;

import entity.Movie;
import entity.MovieList;

import java.util.List;

public interface MovieListDataAccessInterface {
    void createNewList(String userId, String listName, boolean isPublic);
    void addMovieToList(String listName, Movie movie);
    void removeMovieFromList(String listName, Movie movie);
    void saveMovieList(MovieList movieList);
    void renameList(String listId, String newName);
    MovieList getMovieListByName(String listName);
    List<MovieList> getUserListsForUser(String userId);
    void deleteMovieList(String listName);
    void updateMovieList(MovieList movieList);
}
