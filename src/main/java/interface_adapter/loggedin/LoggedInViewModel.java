package interface_adapter.loggedin;

import interface_adapter.ViewModel;
import interface_adapter.get_currentuser.GetCurrentUserController;
import use_case.get_currentuser.GetCurrentUserOutputData;

/**
 * The View Model for the Logged In View.
 */
public class LoggedInViewModel extends ViewModel<LoggedInState> {

    public LoggedInViewModel() {
        super("logged in");
        setState(new LoggedInState());
    }

}
