package use_case.filter_category_selection;

import entity.Movie;

import java.util.List;

public class FilterCategorySelectionOutputData {

    private String categoryName;
    private String[] categoryOptions;
    private List<String> selectedOptions;
    private List<Movie> originalMovieList;

    public FilterCategorySelectionOutputData(String categoryName, String[] categoryOptions, List<Movie> originalList) {
        this.categoryName = categoryName;
        this.categoryOptions = categoryOptions;
        this.originalMovieList = originalList;
    }

    public String[] getCategoryOptions() {
        return this.categoryOptions;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public List<Movie> getOriginalMovieList() {
        return this.originalMovieList;
    }
}
