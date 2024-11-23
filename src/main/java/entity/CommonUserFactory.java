package entity;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password,String favMovie, String favDirector) {
        return new CommonUser(name, password, favMovie, favDirector);
    }
}
