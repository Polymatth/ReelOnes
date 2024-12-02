package use_case.filter_category_selection;

/**
 * The input boundary for the filter category selection use case
 */
public interface FilterCategorySelectionInputBoundary {

    /**
     * Execute the Filter Category Selection Use Case.
     * @param filterCategorySelectionInputData the input data for this use case.
     */
    void execute(FilterCategorySelectionInputData filterCategorySelectionInputData);
}
