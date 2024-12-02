package use_case.movie_list;

import entity.Movie;
import entity.MovieList;
import entity.User;

import java.util.List;

public interface MovieListDataAccessInterface {

    void saveMovieList(User user);

    MovieList getMovieListByName(String listName);

    List<MovieList> getUserListsForUser(String userId);

//    List<MovieList> getMovieListsForUser(String username);
//    void addMovieList(String username, MovieList movieList);
//    void updateMovieList(String username, MovieList movieList);
//    void deleteMovieList(String username, String listName);
}
