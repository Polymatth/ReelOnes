package use_case.change_password;

import entity.MovieList;
import entity.RecommendedList;
import entity.User;
import entity.UserFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The Change Password Interactor.
 */
public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    private final ChangePasswordUserDataAccessInterface userDataAccessObject;
    private final ChangePasswordOutputBoundary userPresenter;
    private final UserFactory userFactory;
    private List<MovieList> movieListList = new ArrayList<>();

    public ChangePasswordInteractor(ChangePasswordUserDataAccessInterface changePasswordDataAccessInterface,
                                    ChangePasswordOutputBoundary changePasswordOutputBoundary,
                                    UserFactory userFactory) {
        this.userDataAccessObject = changePasswordDataAccessInterface;
        this.userPresenter = changePasswordOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(ChangePasswordInputData changePasswordInputData) {
        this.movieListList.add(new RecommendedList(changePasswordInputData.getUsername()));
        final User user = userFactory.create(changePasswordInputData.getUsername(),
                                             changePasswordInputData.getPassword(), changePasswordInputData.getFavMovie(), changePasswordInputData.getFavDirector(), movieListList);
        userDataAccessObject.changePassword(user);

        final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getName(),
                                                                                  false);
        userPresenter.prepareSuccessView(changePasswordOutputData);
    }

    @Override
    public void switchToUserView() {userPresenter.switchToUserView();
    }
}
