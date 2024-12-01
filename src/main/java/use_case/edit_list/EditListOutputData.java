package use_case.edit_list;

import entity.Movie;
import entity.MovieList;

import java.util.List;

/**
 * Output Data for the Edit List Use Case.
 */
public class EditListOutputData {
    private final String updatedListName;
    private final MovieList updatedMovies;

    public EditListOutputData(String updatedListName, MovieList updatedMovies) {
        this.updatedListName = updatedListName;
        this.updatedMovies = updatedMovies;
    }

    public String getUpdatedListName() {
        return updatedListName;
    }

    public MovieList getUpdatedMovies() {
        return updatedMovies;
    }
}
