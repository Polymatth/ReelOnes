package use_case.clear_filters;

/**
 * The input boundary for clearing all selected filters in all categories.
 */
public interface ClearAllFiltersInputBoundary {

    /**
     * Executes the clear all filters use case
     * @param clearAllFiltersInputData the clear all filters input data
     */
    public void execute(ClearAllFiltersInputData clearAllFiltersInputData);

}
