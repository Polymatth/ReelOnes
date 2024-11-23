package interface_adapter.filter_category;

import interface_adapter.ViewModel;

public class FilterCategoryViewModel extends ViewModel<FilterCategoryState> {

    public FilterCategoryViewModel() {
        super("filter category page");
        setState(new FilterCategoryState());
    }
}
