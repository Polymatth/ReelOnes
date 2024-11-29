package use_case.filter_application;

import entity.Movie;

import java.util.List;

public class FilterApplicationOutputData {

    private List<Movie> applicableMovies;

    public FilterApplicationOutputData(List<Movie> applicableMovies) {
        this.applicableMovies = applicableMovies;
    }

    public List<Movie> getApplicableMovies() {
        return this.applicableMovies;
    }

}
