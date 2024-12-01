package use_case.clear_filters;

/**
 * The output boundary for the clear all filters use case
 */
public interface ClearAllFiltersOutputBoundary {

    /**
     * Prepares the success view for the clear all filters use case.
     * @param clearAllFiltersOutputData the clear all filters output data
     */
    public void executeClearAllFilters(ClearAllFiltersOutputData clearAllFiltersOutputData);
}
