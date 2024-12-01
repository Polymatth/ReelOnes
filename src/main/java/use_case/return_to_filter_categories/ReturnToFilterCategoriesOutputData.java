package use_case.return_to_filter_categories;

import entity.Movie;

import java.util.List;

/**
 * The output data for the return to filter categories use case.
 */
public class ReturnToFilterCategoriesOutputData {

    private String categoryName;
    private List<String> selectedOptions;
    private List<Movie> filteredMovies;

    public ReturnToFilterCategoriesOutputData(String categoryName, List<String> selectedOptions,
                                              List<Movie> filteredMovies) {
        this.categoryName = categoryName;
        this.selectedOptions = selectedOptions;
        this.filteredMovies = filteredMovies;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public List<String> getSelectedOptions() {
        return this.selectedOptions;
    }

    public List<Movie> getFilteredMovies() {
        return this.filteredMovies;
    }
}
