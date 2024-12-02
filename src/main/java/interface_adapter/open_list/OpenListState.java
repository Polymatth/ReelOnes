package interface_adapter.open_list;

import entity.Movie;
import entity.UserList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenListState {

    private String listName = "";
    private List<Movie> movies;
    private Boolean isFiltered = false;
    private List<Movie> currentFilteredMovies = new ArrayList<>();
    private Map<String, List<String>> filtersToSelections = new HashMap<>();
    private Map<String, List<Movie>> filtersToMovies = new HashMap<>();


    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Map<String, List<String>> getFiltersToSelections() {
        return this.filtersToSelections;
    }

    public Map<String, List<Movie>> getFiltersToMovies() {
        return this.filtersToMovies;
    }

    public Boolean getFiltered() {
        return this.isFiltered;
    }

    public List<Movie> getCurrentFilteredMovies() {
        return this.currentFilteredMovies;
    }

    public void setFiltersToSelections(Map<String, List<String>> filtersToSelections) {
        this.filtersToSelections = filtersToSelections;
    }

    public void setCurrentFilteredMovies(List<Movie> currentFilteredMovies) {
        this.currentFilteredMovies = currentFilteredMovies;
    }

    public void setFiltersToMovies(Map<String, List<Movie>> filtersToMovies) {
        this.filtersToMovies = filtersToMovies;
    }

}
