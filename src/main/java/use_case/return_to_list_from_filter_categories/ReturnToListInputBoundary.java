package use_case.return_to_list_from_filter_categories;

/**
 * The input boundary for returning to the list from the filter categories view.
 */
public interface ReturnToListInputBoundary {

    /**
     * Executes the return to list from filter categories use case
     * @param returnToListInputData the return to list from filter categories input data
     */
    public void execute(ReturnToListInputData returnToListInputData);
}
