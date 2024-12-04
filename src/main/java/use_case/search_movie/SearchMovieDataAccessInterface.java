package use_case.search_movie;

import entity.Movie;

import java.util.Collection;
import java.util.List;

/**
 * Data Access Interface for the Search Movie Use Case.
 */
public interface SearchMovieDataAccessInterface {
    List<Movie> searchMoviesByQuery(String query);

    List<Movie> searchByDirector(String favDirector);

    List<Movie> searchMoviesByGenre(List<Integer> genreIds);

    List<Movie> getPopularMovies();

    String getDirector(int id);

    Collection<String> getCast(int id);

    Collection<String> getCrew(int id);
}
