package interface_adapter.filter_categories;

import interface_adapter.ViewModel;

public class FilterCategoriesViewModel extends ViewModel<FilterCategoriesState> {

    public FilterCategoriesViewModel() {
        super("filter categories page");
        setState(new FilterCategoriesState());
    }
}

