package data_access;

import use_case.add_movie_to_list.AddMovieDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryAddMovieDataAccessObject implements AddMovieDataAccessInterface {
    private final Map<String, List<String>> userLists = new HashMap<>();

    @Override
    public void addMovieToList(String listId, String movieId) {
        userLists.putIfAbsent(listId, new ArrayList<>());
        userLists.get(listId).add(movieId);
    }
}