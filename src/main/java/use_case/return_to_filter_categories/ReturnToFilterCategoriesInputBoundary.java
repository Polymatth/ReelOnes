package use_case.return_to_filter_categories;

/**
 * The input boundary for returning to the filter categories view from the filter category view.
 */
public interface ReturnToFilterCategoriesInputBoundary {

    /**
     * Executes the return to filter categories use case,
     * @param returnToFilterCategoriesInputData the return to filter categories input data
     */
    public void execute(ReturnToFilterCategoriesInputData returnToFilterCategoriesInputData);
}
