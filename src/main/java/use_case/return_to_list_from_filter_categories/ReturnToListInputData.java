package use_case.return_to_list_from_filter_categories;

import entity.Movie;

import java.util.List;
import java.util.Map;

public class ReturnToListInputData {

    private Map<String, List<String>> filtersToSelections;
    private Map<String, List<Movie>> filtersToMovies;
    private String listView;

    public ReturnToListInputData(Map<String, List<String>> filtersToSelections, Map<String,
            List<Movie>> filtersToMovies, String listView) {
        this.listView = listView;
        this.filtersToMovies = filtersToMovies;
        this.filtersToSelections = filtersToSelections;
    }

    public String getListView() {
        return this.listView;
    }

    public Map<String, List<String>> getFiltersToSelections() {
        return this.filtersToSelections;
    }

    public Map<String, List<Movie>> getFiltersToMovies() {
        return this.filtersToMovies;
    }
}
