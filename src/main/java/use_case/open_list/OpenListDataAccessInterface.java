package use_case.open_list;

import entity.Movie;
import java.util.List;

/**
 * Data Access Interface for the Open List Use Case.
 */
public interface OpenListDataAccessInterface {
    List<Movie> getMoviesForList(String listName);
}
