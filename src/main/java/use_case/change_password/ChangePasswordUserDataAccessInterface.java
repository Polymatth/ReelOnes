package use_case.change_password;

import entity.User;

/**
 * The interface of the DAO for the Change Password Use Case.
 */
public interface ChangePasswordUserDataAccessInterface {

    /**
     * Updates the system to record this user's password.
     * @param user the user whose password is to be updated
     */
    void changePassword(User user);

    /**
     *Saves user to the system.
     * @param user the user who will be saved.
     */
    void save(User user);

    User get(String username);

}
