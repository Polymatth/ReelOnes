package interface_adapter.userprofile;
import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;

public class UserProfileViewModel  extends ViewModel<UserProfileState> {

    public UserProfileViewModel() {
        super("userprofile");
        setState(new UserProfileState());
    }

}
