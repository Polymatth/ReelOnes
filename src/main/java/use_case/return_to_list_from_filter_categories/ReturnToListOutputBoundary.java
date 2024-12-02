package use_case.return_to_list_from_filter_categories;

/**
 * The output boundary for the return to list from filter categories use case
 */
public interface ReturnToListOutputBoundary {

    /**
     * Prepares the list view to return to from the filter categories view
     * @param returnToListOutputData the return to list from filter categories output data
     */
    public void executeReturnToList(ReturnToListOutputData returnToListOutputData);
}
