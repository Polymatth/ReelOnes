package use_case.filter_application;

import use_case.filter_category_selection.FilterCategorySelectionOutputData;

/**
 * The output boundary for the filter application use case
 */
public interface FilterApplicationOutputBoundary {

    void updateFilteredList(FilterApplicationOutputData filterApplicationOutputData);
}
