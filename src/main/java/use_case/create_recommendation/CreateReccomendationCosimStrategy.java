package use_case.create_recommendation;

import entity.RecommendedList;
import entity.User;

public class CreateReccomendationCosimStrategy implements CreateRecommendationStrategy {
    @Override
    public String getStrategy() {
        return "Cosine Similarity";
    }

    @Override
    public RecommendedList generateList(User user) {
        return null;
    }
}
