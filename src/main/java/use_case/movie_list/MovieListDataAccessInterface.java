package use_case.movie_list;

import entity.MovieList;
import entity.User;

import java.util.List;

/**
 * Data Access Interface for the Movie List Use Case.
 */
public interface MovieListDataAccessInterface {

    void saveMovieList(User user);

    MovieList getMovieListByName(String listName, String username);

    List<MovieList> getUserListsForUser(String userId);

//    void addMovieList(String username, MovieList movieList);
//    void updateMovieList(String username, MovieList movieList);
//    void deleteMovieList(String username, String listName);
}
