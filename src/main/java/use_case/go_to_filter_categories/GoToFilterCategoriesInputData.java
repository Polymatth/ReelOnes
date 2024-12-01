package use_case.go_to_filter_categories;

import entity.Movie;

import java.util.List;
import java.util.Map;

/**
 * The Input Data for the Go To Filter Categories use case
 */
public class GoToFilterCategoriesInputData {

    private List<Movie> originalList;
    private Map<String, List<Movie>> filtersToMovies;
    private Map<String, List<String>> filtersToSelections;
    private String listView;

    public GoToFilterCategoriesInputData(List<Movie> originalList, Map<String,
            List<Movie>> filtersToMovies, Map<String, List<String>> filtersToSelections, String listView) {
        this.originalList = originalList;
        this.filtersToMovies = filtersToMovies;
        this.filtersToSelections = filtersToSelections;
        this.listView = listView;
    }

    public List<Movie> getOriginalList() {
        return this.originalList;
    }

    public Map<String, List<Movie>> getFiltersToMovies() {
        return this.filtersToMovies;
    }

    public Map<String, List<String>> getFiltersToSelections() {
        return this.filtersToSelections;
    }

    public String getListView() {
        return this.listView;
    }
}
