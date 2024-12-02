package interface_adapter.movie_list;

import entity.Movie;
import entity.MovieList;
import entity.UserList;

import java.util.List;
/**
 * The State of the Movie List.
 */
public class MovieListState {
    private String name = "";
    private List<Movie> movies;
    private String movieListError;
    private List<MovieList> movieLists;

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<Movie> getMovies(){
        return this.movies;
    }

    public String getMovieListError() {
        return movieListError;
    }

    public void setMovieListError(String movieListError) {
        this.movieListError = movieListError;
    }

    public List<MovieList> getUserLists() {
        return movieLists;
    }

    public void setUserLists(List<MovieList> movieLists) {this.movieLists = movieLists;}
}
