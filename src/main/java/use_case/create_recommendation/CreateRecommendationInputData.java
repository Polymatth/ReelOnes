package use_case.create_recommendation;

import entity.MovieList;

import java.util.List;

public class CreateRecommendationInputData {
    private final String favMovie;

    private final String favDirector;

    private final List<MovieList> movieLists;

    public CreateRecommendationInputData(String favMovie, String favDirector, List<MovieList> movieLists) {
        this.favMovie = favMovie;
        this.favDirector = favDirector;
        this.movieLists = movieLists;
    }

    String getFavMovie() {return favMovie;}

    String getFavDirector() {return favDirector;}

    List<MovieList> getMovieLists() {return movieLists;}
}
