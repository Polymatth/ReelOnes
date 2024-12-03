package data_access;

import entity.Movie;
import use_case.open_list.OpenListDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryOpenListDataAccessObject implements OpenListDataAccessInterface {
    private final Map<String, List<Movie>> movieLists = new HashMap<>();

    @Override
    public List<Movie> getMoviesForList(String listId) {
        // Return the movies in the list; return an empty list if the list ID is not found.
        return movieLists.getOrDefault(listId, new ArrayList<>());
    }

    public void addList(String listId, List<Movie> movies) {
        movieLists.put(listId, movies);
    }
}
