package interface_adapter.fetch_nowplayingmovies;

import entity.Movie;
import interface_adapter.ViewModel;
import interface_adapter.search_movie.SearchMovieState;

import java.util.List;

public class FetchNowPlayingMoviesViewModel extends ViewModel<FetchNowPlayingMoviesState> {

    public FetchNowPlayingMoviesViewModel() {
            super("movie search");
            setState(new FetchNowPlayingMoviesState());
        }

        public void setMovies(List< Movie > movies) {
            getState().setMovies(movies);
        }

        public void setErrorMessage(String errorMessage) {
            getState().setErrorMessage(errorMessage);
        }
}
