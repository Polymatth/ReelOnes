package interface_adapter.filter_categories;

import entity.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterCategoriesState {

    private List<Movie> originalMovieList;
    private Map<String, List<Movie>> filterToMovies = new HashMap<>();
    private Map<String, List<String>> selectedFilters = new HashMap<>();

    public void FilterCategoriesState() {
        for (String category : new String[]{"Genre", "Decade of Release", "Streaming Services", "Rating"}) {
            this.filterToMovies.put(category, new ArrayList<>());
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

    public List<Movie> getOriginalMovieList() {
        return this.originalMovieList;
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
