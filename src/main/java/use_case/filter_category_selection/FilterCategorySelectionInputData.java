package use_case.filter_category_selection;

import entity.Movie;

import java.util.List;

public class FilterCategorySelectionInputData {

    private final String categoryName;
    private final String[] categoryOptions;
    private List<Movie> originalMovieList;

    public FilterCategorySelectionInputData(String categoryName, String[] categoryOptions, List<Movie> originalList) {
        this.categoryName = categoryName;
        this.categoryOptions = categoryOptions;
        this.originalMovieList = originalList;
    }

    public String getCategoryName(){
        return this.categoryName;
    }

    public String[] getCategoryOptions() {
        return this.categoryOptions;
    }

    public List<Movie> getOriginalMovieList() {
        return this.originalMovieList;
    }
}
