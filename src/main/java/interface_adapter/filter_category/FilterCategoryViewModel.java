package interface_adapter.filter_category;

import interface_adapter.ViewModel;

/**
 * The view model for the filter category view.
 */
public class FilterCategoryViewModel extends ViewModel<FilterCategoryState> {

    public FilterCategoryViewModel() {
        super("filter category page");
        setState(new FilterCategoryState());
    }
}
