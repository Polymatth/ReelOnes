package use_case.change_favorites;

/**
 * The output boundary for the Change Favorites Use Case.
 */

public interface ChangeFavoritesOutputBoundary {
    /**
     * Prepares the success view for the Change Password Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ChangeFavoritesOutputData outputData);

    /**
     * Prepares the failure view for the Change Favorites Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

}
