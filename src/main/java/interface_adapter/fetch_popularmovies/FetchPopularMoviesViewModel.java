package interface_adapter.fetch_popularmovies;

import entity.Movie;
import interface_adapter.ViewModel;
import interface_adapter.fetch_nowplayingmovies.FetchNowPlayingMoviesState;

import java.util.List;

public class FetchPopularMoviesViewModel extends ViewModel<FetchPopularMoviesState> {

    public FetchPopularMoviesViewModel() {
        super("movie search");
        setState(new FetchPopularMoviesState());
    }

    public void setMovies(List<Movie> movies) {
        getState().setMovies(movies);
    }

    public void setErrorMessage(String errorMessage) {
        getState().setErrorMessage(errorMessage);
    }
}
