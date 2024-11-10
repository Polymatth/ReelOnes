package use_case.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {

    private String currentUserName;

    public LogoutInputData(String username) {
        this.currentUserName = username;
        // TODO: save the current username in an instance variable and add a getter.
    }

    public String getCurrentUserName() {
        return currentUserName;
    }

}
