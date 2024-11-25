package use_case.filter_application;

import entity.Movie;

import java.util.List;
import java.util.Map;

public class FilterApplicationInputData {

    private final String categoryName;
    private final List<String> optionsSelected;
    private final List<String> allOptions;
    private final List<Movie> originalList;

    public FilterApplicationInputData(String categoryName, List<String> optionsSelected, List<String> allOptions,
                                      List<Movie> originalList) {
        this.categoryName = categoryName;
        this.optionsSelected = optionsSelected;
        this.allOptions = allOptions;
        this.originalList = originalList;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public List<Movie> getOriginalList() {
        return this.originalList;
    }

    public List<String> getOptionsSelected() {
        return this.optionsSelected;
    }

    public List<String> getAllOptions() {
        return this.allOptions;
    }
}
