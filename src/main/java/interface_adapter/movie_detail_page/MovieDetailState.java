package interface_adapter.movie_detail_page;

import entity.Movie;
import entity.MovieList;

import java.util.ArrayList;
import java.util.List;

/**
 * The state for the Movie Detail View Model
 */
public class MovieDetailState {

    private String title = "";
    private String genre = "";
    private String director = "";
    private String year = "";
    private String streamingServices = "";
    private String posterImagePath = "";
    private String originView;
    private String username = "";
    private String loginError;
    private String password = "";
    private String favMovie;
    private String favDirector;
    private List<MovieList> movieListsList = new ArrayList<>();
    private Movie movie;

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

    public Movie getMovie(){return movie;}

    public void setMovie(Movie movie){this.movie = movie;}

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


    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setStreamingServices(String streamingServices) {
        this.streamingServices = streamingServices;
    }

    public void setPosterImagePath(String path) {
        this.posterImagePath = path;
    }

    public String getTitle() {
        return this.title;
    }

    public String getYear() {
        return this.year;
    }

    public String getGenre() {
        if (this.genre.equals("")) {
            return "Unknown";
        }
        return this.genre;
    }

    public String getDirector() {
        if (this.director.equals("")) {
            return "Unknown";
        }
        return this.director;
    }

    public String getStreamingServices() {
        if (this.streamingServices.equals("")) {
            return "Unavailable";
        }
        return this.streamingServices;
    }

    public String getPosterImagePath() {
        return this.posterImagePath;
    }

    public String getOriginView() {
        return this.originView;
    }

    public void setOriginView(String originView) {
        this.originView = originView;
    }
}
