package use_case.go_to_filter_categories;

/**
 * The output boundary for the Go To Filter Categories use case.
 */
public interface GoToFilterCategoriesOutputBoundary {

    /**
     * Prepares the filter categories view.
     * @param goToFilterCategoriesOutputData the filter categories output data
     */
    public void goToFilterCategories(GoToFilterCategoriesOutputData goToFilterCategoriesOutputData);
}
