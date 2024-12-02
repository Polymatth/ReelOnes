package use_case.create_recommendation;

import data_access.MovieAPIAccess;
import entity.Movie;
import entity.MovieList;
import entity.RecommendedList;

import java.util.List;

public interface CreateRecommendationStrategy {

    void setAPIAccess(MovieAPIAccess movieAPIAccess);

    String getStrategy();

    Boolean movieUnknown(List<MovieList> movieLists, Movie movie);

    RecommendedList generateList(String userId, String favMovie, String favDirector, List<MovieList> movieLists, Integer size);


}
