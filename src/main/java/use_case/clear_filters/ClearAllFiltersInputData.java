package use_case.clear_filters;

import entity.Movie;

import java.util.List;
import java.util.Map;

public class ClearAllFiltersInputData {

    private Map<String, List<String>> filtersToSelections;
    private Map<String, List<Movie>> filtersToMovies;

    public ClearAllFiltersInputData(Map<String, List<String>> filtersToSelections, Map<String, List<Movie>>
            filtersToMovies) {
        this.filtersToSelections = filtersToSelections;
        this.filtersToMovies = filtersToMovies;
    }

    public Map<String, List<String>> getFiltersToSelections() {
        return this.filtersToSelections;
    }

    public Map<String, List<Movie>> getFiltersToMovies() {
        return this.filtersToMovies;
    }
}
