package interface_adapter.userprofile;

import entity.Movie;
import entity.MovieList;

import java.util.ArrayList;
import java.util.List;

public class UserProfileState {
    private String username = "";
    private String loginError;
    private String password = "";
    private String favMovie;
    private String favDirector;
    private List<MovieList> movieListsList = new ArrayList<>();

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

    public void setMovieListsList(List<MovieList> movieListsList ){this.movieListsList = movieListsList;}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MovieList> getUserLists() {
        return this.movieListsList;
    }

    public boolean movieListExists(String listName) {
        for (MovieList list : movieListsList) {
            if (list.getListName().equals(listName)) {
                return true;
            }
        }
        return false;
    }

}
