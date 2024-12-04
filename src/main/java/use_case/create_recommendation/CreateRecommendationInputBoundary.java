package use_case.create_recommendation;

/**
 * Input Boundary for Create Recommendation Use Case.
 */
public interface CreateRecommendationInputBoundary {
    /**
     * Execute the CreateRecommendation Use Case.
     * @param createRecommendationInputData the input data for this use case
     */
    void execute (CreateRecommendationInputData createRecommendationInputData);

}
