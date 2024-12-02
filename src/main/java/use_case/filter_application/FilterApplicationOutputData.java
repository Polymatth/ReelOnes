package use_case.filter_application;

import entity.Movie;

import java.util.List;

/**
 * Output data for the filter application use case.
 */
public class FilterApplicationOutputData {

    private List<Movie> applicableMovies;
    private List<String> optionsSelected;
    private String categoryName;

    public FilterApplicationOutputData(List<Movie> applicableMovies, List<String> optionsSelected, String categoryName) {
        this.applicableMovies = applicableMovies;
        this.optionsSelected = optionsSelected;
        this.categoryName = categoryName;
    }

    public List<Movie> getApplicableMovies() {
        return this.applicableMovies;
    }

    public List<String> getOptionsSelected() {
        return this.optionsSelected;
    }

    public String getCategoryName(){
        return this.categoryName;
    }
}
