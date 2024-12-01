package use_case.edit_list;

import entity.MovieList;

/**
 * Data Access Interface for the Edit List Use Case.
 */
public interface EditListDataAccessInterface {
    MovieList getList(String username, String listName);

    void updateList(String username, MovieList movieList);
}