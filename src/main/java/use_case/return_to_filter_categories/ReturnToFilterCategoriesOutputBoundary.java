package use_case.return_to_filter_categories;

/**
 * The output boundary for the return to filter categories use case.
 */
public interface ReturnToFilterCategoriesOutputBoundary {

    /**
     * Prepares the filter categories view in order to return from the filter category view.
     * @param returnToFilterCategoriesOutputData the return to filter categories output data
     */
    public void executeReturnToFilterCategories(ReturnToFilterCategoriesOutputData returnToFilterCategoriesOutputData);
}
