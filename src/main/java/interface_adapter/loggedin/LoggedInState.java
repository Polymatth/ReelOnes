package interface_adapter.loggedin;

import entity.Movie;
import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesOutputData;

import java.util.List;

/**
 * The State information representing the logged-in user.
 */
public class LoggedInState {
    private String username ="";
    private String loginError;
    private String password="";
    private String favMovie;
    private String favDirector;
    private List<Movie> nowPlayingMovies;
    private List<Movie> popularMovies;
    private String errorMessage;


    public List<Movie> getNowPlayingMovies() {
        return nowPlayingMovies;
    }

    public void setNowPlayingMovies(List<Movie> nowPlayingMovies) {
        this.nowPlayingMovies = nowPlayingMovies;
    }

    public List<Movie> getPopularMovies() {
        return popularMovies;
    }
    public void setPopularMovies(List<Movie> popularMovies) {this.popularMovies = popularMovies;}

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUsername() {
        return username;}

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
