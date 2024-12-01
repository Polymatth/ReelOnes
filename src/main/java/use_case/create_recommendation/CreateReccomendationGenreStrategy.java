package use_case.create_recommendation;

import entity.RecommendedList;
import entity.User;

public class CreateReccomendationGenreStrategy implements CreateRecommendationStrategy {
    @Override
    public String getStrategy() {
        return "Genre Strategy";
    }

    @Override
    public RecommendedList generateList(User user) {
        return null;
    }
}
