package interface_adapter.fetch_nowplayingmovies;

import entity.Movie;
import java.util.List;


public class FetchNowPlayingMoviesState {

    private List<Movie> movies;
    private String errorMessage;


    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
