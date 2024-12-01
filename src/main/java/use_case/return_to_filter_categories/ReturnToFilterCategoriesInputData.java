package use_case.return_to_filter_categories;

import entity.Movie;

import java.util.List;

/**
 * The input data for the return to filter categories use case
 */
public class ReturnToFilterCategoriesInputData {

    private String categoryName;
    private List<String> selectedOptions;
    private List<Movie> filteredList;

    public ReturnToFilterCategoriesInputData(String categoryName, List<String> selectedOptions,
                                             List<Movie> filteredList) {
        this.categoryName = categoryName;
        this.selectedOptions = selectedOptions;
        this.filteredList = filteredList;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public List<String> getSelectedOptions() {
        return this.selectedOptions;
    }

    public List<Movie> getFilteredList() {
        return this.filteredList;
    }
}
