package interface_adapter.change_password;

import interface_adapter.ViewModel;
import interface_adapter.loggedin.LoggedInState;

/**
 * The View Model for the ChangePassword View.
 */
public class ChangePasswordViewModel extends ViewModel<ChangePasswordState> {

    public ChangePasswordViewModel() {
        super("change password");
        setState(new ChangePasswordState());
    }
}
