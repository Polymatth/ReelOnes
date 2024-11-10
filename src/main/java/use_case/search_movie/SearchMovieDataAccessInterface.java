package use_case.search_movie;

import entity.Movie;
import java.util.List;

public interface SearchMovieDataAccessInterface {
    List<Movie> searchMoviesByQuery(String query);
}
