package interface_adapter.search_movie;

import entity.Movie;
import interface_adapter.ViewModel;
import java.util.List;

public class SearchMovieViewModel extends ViewModel<SearchMovieState> {

    public SearchMovieViewModel() {
        super("movie search");
        setState(new SearchMovieState());
    }

    public void setMovies(List<Movie> movies) {
        getState().setMovies(movies);
    }

    public void setErrorMessage(String errorMessage) {
        getState().setErrorMessage(errorMessage);
    }
}
