package interface_adapter.filter_categories;

import entity.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterCategoriesState {

    private List<Movie> movieList;
    private Map<String, List<String>> selectedFilters = new HashMap<>();

    public void setMovieList(List<Movie> list) {
        this.movieList = list;
    }

    public List<Movie> getMovieList() {
        return this.movieList;
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
