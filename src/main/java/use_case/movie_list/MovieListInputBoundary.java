package use_case.movie_list;

import entity.MovieList;

import java.util.List;

/**
 * Input Boundary for actions which are related to Movie Lists use case.
 */
public interface MovieListInputBoundary {

    void execute(MovieListInputData movieListInputData);

    List<MovieList> getUserListsForUser(String userId);

    boolean movieListExists(String listName, String username);

}
