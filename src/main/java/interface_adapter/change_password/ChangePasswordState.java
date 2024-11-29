package interface_adapter.change_password;

/**
 * The State information representing the logged-in user when they are changing their password.
 */

public class ChangePasswordState {
    private String username ;
    private String favMovie;
    private String favDirector;
    private String password= "";
    private String passwordError;

    public ChangePasswordState(ChangePasswordState copy) {
        username = copy.username;
        password = copy.password;
        favMovie = copy.favMovie;
        favDirector = copy.favDirector;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ChangePasswordState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFavMovie(String favMovie) {this.favMovie = favMovie;}

    public void setFavDirector(String favDirector) {this.favDirector = favDirector;}

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPassword() {
        return password;
    }

    public String getFavMovie() {return favMovie;}

    public String getFavDirector() {return favDirector;}
}
