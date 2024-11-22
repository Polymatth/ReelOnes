package interface_adapter.filter_categories;

import entity.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterCategoriesState {

    private List<Movie> originalMovieList;
    private List<Movie> filteredMovieList = new ArrayList<>();
    private Map<String, List<String>> selectedFilters = new HashMap<>();

    public void setOriginalMovieList(List<Movie> list) {
        this.originalMovieList = list;
    }

    public List<Movie> getOriginalMovieList() {
        return this.originalMovieList;
    }

    public void addMovieToFilteredList(Movie movie) {
        this.filteredMovieList.add(movie);
    }

    public List<Movie> getFilteredMovieList() {
        return this.filteredMovieList;
    }

    public void addNewFilters(String filterCategory, String filterOption) {
        if (this.selectedFilters.keySet().contains(filterCategory)) {
            if (!this.selectedFilters.get(filterCategory).contains(filterOption)) {
                this.selectedFilters.get(filterCategory).add(filterOption);
            }
        }
        else {
            List<String> newValueList = new ArrayList<>();
            newValueList.add(filterOption);
            this.selectedFilters.put(filterCategory, newValueList);
        }
    }

    public Map<String, List<String>> getSelectedFilters() {
        return this.selectedFilters;
    }
}
