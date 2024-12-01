package use_case.create_recommendation;

import entity.Movie;
import entity.RecommendedList;

import java.util.List;

public class CreateRecommendationOutputData {

    private final RecommendedList recommendedList;

    public CreateRecommendationOutputData(RecommendedList recommendedList) {this.recommendedList = recommendedList;}

    public String getName() {return this.recommendedList.getListName();}

    public List<Movie> getMovies() {return this.recommendedList.getMovies();}

    public RecommendedList getRecommendedList() {return this.recommendedList;}

}
