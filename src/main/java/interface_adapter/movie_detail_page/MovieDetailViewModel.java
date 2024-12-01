package interface_adapter.movie_detail_page;

import interface_adapter.ViewModel;

/**
 * The view model for the movie detail view.
 */
public class MovieDetailViewModel extends ViewModel<MovieDetailState> {

    public MovieDetailViewModel() {
        super("movie detail page");
        setState(new MovieDetailState());
    }
}
