package use_case.go_to_filter_categories;

/**
 * The input boundary for actions related to entering to filter categories view.
 */
public interface GoToFilterCategoriesInputBoundary {
    /**
     * Executes the go to filter categories use case
     * @param goToFilterCategoriesInputData the filter categories data
     */
    public void execute(GoToFilterCategoriesInputData goToFilterCategoriesInputData);
}
