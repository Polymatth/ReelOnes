package interface_adapter.open_list;

import entity.Movie;
import entity.MovieList;
import entity.UserList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenListState {

    private String listName = "";
    private List<Movie> movies;
    private List<MovieList> movieListList = new ArrayList<MovieList>();


    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<MovieList> getMovieListList(){return movieListList;}

    public void setMovieListList(List<MovieList> movieListList) {
        this.movieListList = movieListList;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
