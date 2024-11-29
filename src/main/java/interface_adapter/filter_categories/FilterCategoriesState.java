package interface_adapter.filter_categories;

import entity.Movie;
import use_case.filter_application.FilterCategoryConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterCategoriesState {

    private List<Movie> originalMovieList;
    private Map<String, List<Movie>> filterToMovies = new HashMap<>();
    private Map<String, List<String>> filtersToSelections = new HashMap<>();
    private String listView;

    public void FilterCategoriesState() {
        for (String category : FilterCategoryConstants.getCategories()) {
            this.filterToMovies.put(category, new ArrayList<>());
            this.filtersToSelections.put(category, new ArrayList<>());
        }
    }

    public void setOriginalMovieList(List<Movie> list) {
        this.originalMovieList = list;
    }

    public Map<String, List<Movie>> getFilterToMovies() {
        return this.filterToMovies;
    }

    public void setFilterToMovies(Map<String, List<Movie>> filterToMovies) {
        this.filterToMovies = filterToMovies;
    }

    public void setMoviestoFilter(String category, List<Movie> movies) {
        this.filterToMovies.replace(category, movies);
    }

    public void setSelectedFilters(String category, List<String> selectedOptions) {
        this.filtersToSelections.replace(category, selectedOptions);
    }

    public List<Movie> getOriginalMovieList() {
        return this.originalMovieList;
    }

    public Map<String, List<String>> getFiltersToSelections() {
        return this.filtersToSelections;
    }

    public void setFiltersToSelections(Map<String, List<String>> filtersToSelections) {
        this.filtersToSelections = filtersToSelections;
    }

    public String getListView() {
        return this.listView;
    }

    public void setListView(String listView) {
        this.listView = listView;
    }
}
