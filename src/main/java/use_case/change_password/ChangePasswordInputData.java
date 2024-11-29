package use_case.change_password;

/**
 * The input data for the Change Password Use Case.
 */
public class ChangePasswordInputData {

    private final String password;
    private final String username;
    private final String favMovie;
    private final String favDirector;

    public ChangePasswordInputData(String username, String password, String favMovie, String favDirector) {
        this.password = password;
        this.username = username;
        this.favMovie = favMovie;
        this.favDirector = favDirector;
    }

    String getPassword() {
        return password;
    }

    String getUsername() {
        return username;
    }

    String getFavMovie() {return favMovie;}

    String getFavDirector() {return favDirector;}


}
