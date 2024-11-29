package use_case.return_to_list_from_filter_categories;

import entity.Movie;

import java.util.List;
import java.util.Map;

public class ReturnToListOutputData {

    private String listView;
    private List<Movie> finalFilteredList;
    private Map<String, List<Movie>> filtersToMovies;
    private Map<String, List<String>> filtersToSelections;

    public ReturnToListOutputData(Map<String, List<Movie>> filtersToMovies,
                                  Map<String, List<String>> filtersToSelections, String listView,
                                  List<Movie> finalFilteredList) {
        this.filtersToMovies = filtersToMovies;
        this.filtersToSelections = filtersToSelections;
        this.listView = listView;
        this.finalFilteredList = finalFilteredList;
    }

    public Map<String, List<Movie>> getFiltersToMovies() {
        return this.filtersToMovies;
    }

    public String getListView() {
        return this.listView;
    }

    public Map<String, List<String>> getFiltersToSelections() {
        return this.filtersToSelections;
    }

    public List<Movie> getFinalFilteredList() {
        return this.finalFilteredList;
    }
}
