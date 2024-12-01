package use_case.clear_filters;

import entity.Movie;

import java.util.List;
import java.util.Map;

public class ClearAllFiltersInputData {

    private Map<String, List<String>> filtersToSelections;
    private Map<String, List<Movie>> filtersToMovies;
    private List<Movie> originalMovieList;

    public ClearAllFiltersInputData(Map<String, List<String>> filtersToSelections, Map<String, List<Movie>>
            filtersToMovies, List<Movie> originalMovieList) {
        this.filtersToSelections = filtersToSelections;
        this.filtersToMovies = filtersToMovies;
        this.originalMovieList = originalMovieList;
    }

    public Map<String, List<String>> getFiltersToSelections() {
        return this.filtersToSelections;
    }

    public Map<String, List<Movie>> getFiltersToMovies() {
        return this.filtersToMovies;
    }

    public List<Movie> getOriginalMovieList() {
        return this.originalMovieList;
    }
}
