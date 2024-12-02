package interface_adapter.movie_list;

import interface_adapter.ViewModel;


/**
 * The View Model for the movieList.
 */
public class MovieListViewModel extends ViewModel<MovieListState> {

    public MovieListViewModel() {
        super("movie list page");
        setState(new MovieListState());
    }
}
