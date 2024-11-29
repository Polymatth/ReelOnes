package interface_adapter.movie_list;

import entity.Movie;
import entity.MovieList;
import interface_adapter.ViewModel;

import java.util.List;

/**
 * The View Model for the movieList View.
 */
public class MovieListViewModel extends ViewModel<MovieListState> {
    private List<MovieList> userLists;
    private List<Movie> moviesInList;

    public MovieListViewModel() {
        super("movie list page");
        setState(new MovieListState());
    }

    public List<MovieList> getUserLists() {
        return userLists;
    }

    public void setUserLists(List<MovieList> userLists) {
        this.userLists = userLists;
    }

    public List<Movie> getMoviesInList() {
        return moviesInList;
    }

    public void setMoviesInList(List<Movie> moviesInList) {
        this.moviesInList = moviesInList;
    }

}
