package use_case.filter_application;

import use_case.filter_category_selection.FilterCategorySelectionOutputData;

public interface FilterApplicationOutputBoundary {

    void updateFilteredList(FilterApplicationOutputData filterApplicationOutputData);
}
