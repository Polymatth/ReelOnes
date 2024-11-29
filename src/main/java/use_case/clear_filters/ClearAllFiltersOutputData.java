package use_case.clear_filters;

import entity.Movie;

import java.util.List;
import java.util.Map;

public class ClearAllFiltersOutputData {
    Map<String, List<Movie>> filtersToMovies;
    Map<String, List<String>> filtersToSelections;

    public ClearAllFiltersOutputData(Map<String, List<Movie>> filtersToMovies,
                                     Map<String, List<String>> filtersToSelections) {
        this.filtersToSelections = filtersToSelections;
        this.filtersToMovies = filtersToMovies;
    }

    public Map<String, List<Movie>> getFiltersToMovies() {
        return this.filtersToMovies;
    }

    public Map<String, List<String>> getFiltersToSelections() {
        return this.filtersToSelections;
    }
}
