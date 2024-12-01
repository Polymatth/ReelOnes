package interface_adapter.fetch_popularmovies;

import entity.Movie;

import java.util.List;

public class FetchPopularMoviesState {
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
