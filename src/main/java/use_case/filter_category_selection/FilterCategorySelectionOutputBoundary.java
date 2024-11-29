package use_case.filter_category_selection;

public interface FilterCategorySelectionOutputBoundary {

    /**
     * Prepares the success view for the Filter Category selection use case.
     * @param filterCategorySelectionOutputData the output data for the use case
     */
    void prepareSuccessView(FilterCategorySelectionOutputData filterCategorySelectionOutputData);
}
