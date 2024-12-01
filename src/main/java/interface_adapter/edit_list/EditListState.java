package interface_adapter.edit_list;

import entity.Movie;
import entity.MovieList;

import java.util.ArrayList;
import java.util.List;

public class EditListState {
    private String listName;
    private List<Movie> movies = new ArrayList<>();
    private String errorMessage;

    // Copy constructor
    public EditListState(EditListState copy) {
        this.listName = copy.listName;
        this.movies = new ArrayList<>(copy.movies); // Create a new list to avoid shared references
        this.errorMessage = copy.errorMessage;
    }

    // Default constructor
    public EditListState() {
    }

    // Getter and Setter for listName
    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    // Getter and Setter for movies
    public List<Movie> getMovies() {
        return new ArrayList<>(movies);
    }

    public void setMovies(MovieList movies) {
        this.movies = movies.getMovies();
    }

    // Getter and Setter for errorMessage
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}