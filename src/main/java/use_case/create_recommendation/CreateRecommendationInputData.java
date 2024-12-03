package use_case.create_recommendation;

import entity.MovieList;
import entity.User;

import java.util.List;

public class CreateRecommendationInputData {
    private final String favMovie;

    private final String favDirector;

    private final List<MovieList> movieLists;

    private final String userId;

    private final String password;

    public CreateRecommendationInputData(String userId, String password, String favMovie, String favDirector, List<MovieList> movieLists) {
        this.userId = userId;
        this.password = password;
        this.favMovie = favMovie;
        this.favDirector = favDirector;
        this.movieLists = movieLists;

    }

    String getFavMovie() {return favMovie;}

    String getFavDirector() {return favDirector;}

    List<MovieList> getMovieLists() {return movieLists;}

    String getUserId() {return userId;}

    String getPassword() {return password;}
}
