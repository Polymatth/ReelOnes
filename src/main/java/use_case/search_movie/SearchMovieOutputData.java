package use_case.search_movie;

import entity.Movie;
import java.util.List;

/**
 * Output data for Search Movies Use Case.
 */
public class SearchMovieOutputData {
    private final List<Movie> movies;

    public SearchMovieOutputData(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
