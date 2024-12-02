package use_case.create_recommendation;

import use_case.clear_filters.ClearAllFiltersOutputData;

public interface CreateRecommendationOutputBoundary {

    void executeCreateRecommendation(CreateRecommendationOutputData createRecommendationOutputData);
}
