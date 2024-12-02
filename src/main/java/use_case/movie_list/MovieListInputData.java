package use_case.movie_list;

import entity.MovieList;

import java.util.List;

/**
 * The Input Data for the MovieList Use Case.
 */
public class MovieListInputData {
    private final List<MovieList> movieListList;
    private final String password;
    private final String username;
    private final String favMovie;
    private final String favDirector;

    public MovieListInputData(String username, String password, String favMovie, String favDirector, List<MovieList> movieListList) {
        this.username = username;
        this.favMovie = favMovie;
        this.favDirector = favDirector;
        this.password = password;
        this.movieListList = movieListList;
    }

    List<MovieList> getMovieListList() {
        return movieListList;
    }

    String getPassword() {return password;}
    String getUsername() {return  username;}
    String getFavMovie(){return favMovie;}
    String getFavDirector(){return favDirector;}



}
