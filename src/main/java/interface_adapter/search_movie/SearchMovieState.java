package interface_adapter.search_movie;

import entity.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchMovieState {
    private List<Movie> movies;
    private String errorMessage;
    private Boolean isFiltered = false;
    private List<Movie> currentFilteredMovies = new ArrayList<>();
    private Map<String, List<String>> filtersToSelections = new HashMap<>();
    private Map<String, List<Movie>> filtersToMovies = new HashMap<>();

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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

    public void setFiltered(Boolean filtered) {
        isFiltered = filtered;
    }

    public List<Movie> moviesToDisplay() {
        if (isFiltered) {
            return this.currentFilteredMovies;
        }
        else {
            return this.movies;
        }
    }
}