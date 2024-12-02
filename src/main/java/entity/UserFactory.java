package entity;

import java.util.List;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new User.
     *
     * @param name the name of the new user
     * @param password the password of the new user
     * @return the new user
     */
    User create(String name, String password,String favMovie,String favDirector, List<MovieList> movieLists);

}
