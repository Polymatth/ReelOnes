package interface_adapter.change_favorites;

import interface_adapter.change_password.ChangePasswordState;

/**
 * The State information representing the logged-in user when they are changing their favorites.
 */

public class ChangeFavoritesState {
    private String username ;
    private String favMovie;
    private String favDirector;
    private String password= "";
    private String passwordError;

    public ChangeFavoritesState(ChangeFavoritesState copy) {
        username = copy.username;
        password = copy.password;
        favMovie = copy.favMovie;
        favDirector = copy.favDirector;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ChangeFavoritesState() {

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
