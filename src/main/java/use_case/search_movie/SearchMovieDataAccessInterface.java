package use_case.search_movie;

import entity.Movie;
import java.util.List;

/**
 * Data Access Interface for the Search Movie Use Case.
 */
public interface SearchMovieDataAccessInterface {
    List<Movie> searchMoviesByQuery(String query);
}
