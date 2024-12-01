package use_case.filter_application;

import entity.Movie;

import java.util.List;

public class FilterApplicationOutputData {

    private List<Movie> applicableMovies;
    private List<String> optionsSelected;

    public FilterApplicationOutputData(List<Movie> applicableMovies, List<String> optionsSelected) {
        this.applicableMovies = applicableMovies;
        this.optionsSelected = optionsSelected;
    }

    public List<Movie> getApplicableMovies() {
        return this.applicableMovies;
    }

    public List<String> getOptionsSelected() {
        return this.optionsSelected;
    }
}
