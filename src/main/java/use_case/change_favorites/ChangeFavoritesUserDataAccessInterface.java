package use_case.change_favorites;

import entity.User;

/**
 * The interface of the DAO for the Change Favorites Use Case.
 */
public interface ChangeFavoritesUserDataAccessInterface {

    /**
     * Updates the system to record this user's new favMovie and favDirector.
     * @param user the user whose password is to be updated
     */
    void modifyInfo(User user);

    void save(User user);
}
