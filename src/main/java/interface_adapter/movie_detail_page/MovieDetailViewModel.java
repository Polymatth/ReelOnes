package interface_adapter.movie_detail_page;

import interface_adapter.ViewModel;

public class MovieDetailViewModel extends ViewModel<MovieDetailState> {

    public MovieDetailViewModel() {
        super("movie detail page");
        setState(new MovieDetailState());
    }
}
