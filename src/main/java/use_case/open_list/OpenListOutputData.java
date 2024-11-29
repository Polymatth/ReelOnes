package use_case.open_list;

import entity.Movie;

import java.util.List;

public class OpenListOutputData {
    private final String listName;
    private final List<Movie> movies;

    public OpenListOutputData(String listName, List<Movie> movies) {
        this.listName = listName;
        this.movies = movies;
    }

    public String getListName() {
        return listName;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
