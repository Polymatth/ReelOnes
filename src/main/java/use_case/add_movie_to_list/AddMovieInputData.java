package use_case.add_movie_to_list;

import entity.MovieList;

import java.util.List;

/**
 * Data class containing information to add a movie to a user list.
 */
public class AddMovieInputData {
    private final String password;
    private final String username;
    private final String favMovie;
    private final String favDirector;
    private final List<MovieList> movieListsList;
    private final String listName;
    private final String movieTitle;


    public AddMovieInputData(String username, String password,String favMovie, String favDirector, List<MovieList> movieListsList, String listName, String movieTitle) {
        this.username = username;
        this.favMovie = favMovie;
        this.favDirector = favDirector;
        this.password = password;
        this.movieListsList = movieListsList;
        this.movieTitle = movieTitle;
        this.listName = listName;
    }

    String getPassword() {
        return password;
    }

    String getUsername() {
        return username;
    }

    String getFavMovie() {return favMovie;}

    String getFavDirector() {return favDirector;}

    String getListName(){return listName;}

    String getMovieTitle(){return movieTitle;}

    List<MovieList> getMovieListsList() {return movieListsList;}
}