package use_case.filter_category_selection;

import entity.Movie;

import java.util.List;
import java.util.Map;

/**
 * The input data for the filter category selection use case
 */
public class FilterCategorySelectionInputData {

    private final String categoryName;
    private final String[] categoryOptions;
    private final List<Movie> originalList;
    private List<Movie> filteredList;
    private List<String> selectedOptions;

    public FilterCategorySelectionInputData(String categoryName, String[] categoryOptions, List<Movie> originalList,
                                            List<Movie> filteredList, List<String> selectedOptions) {
        this.categoryName = categoryName;
        this.categoryOptions = categoryOptions;
        this.originalList = originalList;
        this.filteredList = filteredList;
        this.selectedOptions = selectedOptions;
    }

    public String getCategoryName(){
        return this.categoryName;
    }

    public String[] getCategoryOptions() {
        return this.categoryOptions;
    }

    public List<Movie> getOriginalList() {
        return this.originalList;
    }

    public List<Movie> getFilteredList() {
        return this.filteredList;
    }

    public List<String> getSelectedOptions() {
        return this.selectedOptions;
    }
}
