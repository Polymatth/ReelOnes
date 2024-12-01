package use_case.create_recommendation;

import entity.RecommendedList;
import entity.User;

public interface CreateRecommendationStrategy {

    String getStrategy();

    RecommendedList generateList(User user);


}
