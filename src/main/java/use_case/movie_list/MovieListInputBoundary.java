package use_case.movie_list;

import entity.MovieList;

import java.util.List;

public interface MovieListInputBoundary {

    void execute(MovieListInputData movieListInputData);

    List<MovieList> getUserListsForUser(String userId);

    boolean movieListExists(String listName);

}
