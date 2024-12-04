package data_access;

import entity.MovieList;
import entity.User;
import use_case.add_movie_to_list.AddMovieDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryAddMovieDataAccessObject implements AddMovieDataAccessInterface {
    private final Map<String, User> userStore = new HashMap<>();

//    @Override
//    public void addMovieToList(String listId, String movieId) {
//        userLists.putIfAbsent(listId, new ArrayList<>());
//        userLists.get(listId).add(movieId);
//    }

    @Override
    public void saveMovieList(User user) {
// Simulate saving a user with their movie list to in-memory storage
        if (userStore.containsKey(user.getName())) {
            // If the user already exists, update their movie list
            User existingUser = userStore.get(user.getName());
            existingUser.getMovieLists().addAll(user.getMovieLists());
        } else {
            // If the user does not exist, add them to the store
            userStore.put(user.getName(), user);
        }
    }
    public MovieList findMovieListByUser(String username, String listName) {
        User user = userStore.get(username);
        if (user != null) {
            for (MovieList movieList : user.getMovieLists()) {
                if (movieList.getListName().equals(listName)) {
                    return movieList;
                }
            }
        }
        return null;
    }
    public User getUserByUsername(String username) {
        return userStore.get(username);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userStore.values());
    }
}