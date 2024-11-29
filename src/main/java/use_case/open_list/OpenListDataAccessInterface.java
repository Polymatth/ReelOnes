package use_case.open_list;

import entity.Movie;
import java.util.List;

public interface OpenListDataAccessInterface {
    List<Movie> getMoviesForList(String listName);
}
