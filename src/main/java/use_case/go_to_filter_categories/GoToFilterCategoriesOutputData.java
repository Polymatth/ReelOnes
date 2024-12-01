package use_case.go_to_filter_categories;

import entity.Movie;

import java.util.List;
import java.util.Map;

/**
 * Output data for the go to filter categories use case
 */
public class GoToFilterCategoriesOutputData {

    private List<Movie> originalList;
    private Map<String, List<Movie>> filtersToMovies;
    private Map<String, List<String>> filtersToSelections;
    private String listView;

    public GoToFilterCategoriesOutputData(List<Movie> originalList, Map<String, List<Movie>> filtersToMovies,
                                          Map<String, List<String>> filtersToSelections, String listView) {
        this.originalList = originalList;
        this.filtersToMovies = filtersToMovies;
        this.filtersToSelections = filtersToSelections;
        this.listView = listView;
    }

    public Map<String, List<String>> getFiltersToSelections() {
        return this.filtersToSelections;
    }

    public List<Movie> getOriginalList() {
        return this.originalList;
    }

    public Map<String, List<Movie>> getFiltersToMovies() {
        return this.filtersToMovies;
    }

    public String getListView() {
        return this.listView;
    }
}
