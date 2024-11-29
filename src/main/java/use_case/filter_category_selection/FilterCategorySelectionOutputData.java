package use_case.filter_category_selection;

import entity.Movie;

import java.util.List;
import java.util.Map;

public class FilterCategorySelectionOutputData {

    private String categoryName;
    private String[] categoryOptions;
    private List<String> selectedOptions;
    private List<Movie> originalList;
    private List<Movie> filteredList;

    public FilterCategorySelectionOutputData(String categoryName, String[] categoryOptions, List<Movie> originalList,
                                             List<Movie> filteredList, List<String> selectedOptions) {
        this.categoryName = categoryName;
        this.categoryOptions = categoryOptions;
        this.originalList = originalList;
        this.filteredList = filteredList;
        this.selectedOptions = selectedOptions;
    }

    public String[] getCategoryOptions() {
        return this.categoryOptions;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public List<Movie> getFilteredList() {
        return this.filteredList;
    }

    public List<Movie> getOriginalList() {
        return this.originalList;
    }

    public List<String> getSelectedOptions() {
        return this.selectedOptions;
    }
}
