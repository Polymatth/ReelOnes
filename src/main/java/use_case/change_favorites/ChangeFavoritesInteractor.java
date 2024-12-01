package use_case.change_favorites;

import entity.User;
import entity.UserFactory;
import use_case.change_password.ChangePasswordInputData;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;
import use_case.change_password.ChangePasswordUserDataAccessInterface;

/**
 * The Change Favorites Interactor.
 */
public class ChangeFavoritesInteractor implements  ChangeFavoritesInputBoundary {
    private final ChangeFavoritesUserDataAccessInterface userDataAccessObject;
    private final ChangeFavoritesOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public ChangeFavoritesInteractor(ChangeFavoritesUserDataAccessInterface changeFavoritesUserDataAccessInterface, ChangeFavoritesOutputBoundary changeFavoritesOutputBoundary,
                                    UserFactory userFactory) {
        this.userDataAccessObject = changeFavoritesUserDataAccessInterface;
        this.userPresenter = changeFavoritesOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(ChangeFavoritesInputData changeFavoritesInputData) {
        final User user = userFactory.create(changeFavoritesInputData.getUsername(),
                changeFavoritesInputData.getPassword(), changeFavoritesInputData.getFavMovie(),changeFavoritesInputData.getFavDirector());
        userDataAccessObject.modifyInfo(user);


        final ChangeFavoritesOutputData changeFavoritesOutputData = new ChangeFavoritesOutputData(user.getName(), false);
        userPresenter.prepareSuccessView(changeFavoritesOutputData);
    }

}
