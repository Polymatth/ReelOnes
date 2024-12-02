package use_case.change_favorites;

import entity.MovieList;

import java.util.List;

public class ChangeFavoritesInputData {
    private final String password;
    private final String username;
    private final String favMovie;
    private final String favDirector;
    private final List<MovieList> movieListsList;

    public ChangeFavoritesInputData(String username, String password, String favMovie, String favDirector, List<MovieList> movieListsList) {
        this.password = password;
        this.username = username;
        this.favMovie = favMovie;
        this.favDirector = favDirector;
        this.movieListsList = movieListsList;
    }

    String getPassword() {
        return password;
    }

    String getUsername() {
        return username;
    }

    String getFavMovie() {return favMovie;}

    String getFavDirector() {return favDirector;}

    List<MovieList> getMovieListsList() {return movieListsList;}
}
