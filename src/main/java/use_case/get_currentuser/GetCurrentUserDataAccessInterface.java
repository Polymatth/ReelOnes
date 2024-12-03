package use_case.get_currentuser;

import entity.User;

/**
 * DAO for the Get Current User Use Case.
 */

public interface GetCurrentUserDataAccessInterface {
    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     */
    User get(String username);

    /**
     * Returns the username of the curren user of the application.
     * @return the username of the current user; null indicates that no one is logged into the application.
     */
    String getCurrentUsername();

}
