package interface_adapter.edit_list;


import interface_adapter.ViewModel;

/**
 * The view model for the Edit List view.
 */
public class EditListViewModel extends ViewModel<EditListState> {
    public EditListViewModel() {
        super("edit list");
        setState(new EditListState());
    }
}