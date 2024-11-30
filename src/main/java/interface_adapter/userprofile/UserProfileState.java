package interface_adapter.userprofile;

public class UserProfileState {
    private String username = "";
    private String loginError;
    private String password = "";
    private String favMovie;
    private String favDirector;

    public String getUsername() {
        return username;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getFavDirector() {
        return favDirector;
    }

    public String getFavMovie() {
        return favMovie;
    }

    public void setFavDirector(String favDirector) {
        this.favDirector = favDirector;
    }

    public void setFavMovie(String favMovie) {
        this.favMovie = favMovie;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
