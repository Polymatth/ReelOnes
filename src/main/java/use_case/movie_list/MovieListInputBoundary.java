package use_case.movie_list;

import entity.Movie;
import entity.MovieList;

import java.util.List;

public interface MovieListInputBoundary {

    void execute(MovieListInputData movieListInputData);

    void createNewList(String userId, String listName, Boolean isPublic);

    List<Movie> getMoviesForList(String listName);

    List<MovieList> getUserListsForUser(String userId);

    boolean movieListExists(String listName);

    void updateMovieList(MovieList movieList);

    void addMovieToList(String listName, Movie movie);

    void renameList(String listId, String newName);

    void deleteMovieFromList(String listName, Movie movie);

    void deleteMovieList(String listName);
}
