package use_case.change_password;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangePasswordInteractorTest {

    private static final String FAVMOVIE = "favoriteMovie";
    private static final String FAVDIRECTOR = "favoriteDirector";


    @Test
    void successChangePasswordTest() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("Paul", "newPassword", FAVMOVIE, FAVDIRECTOR);
        ChangePasswordUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add the user with the old password to the repository
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "oldPassword", FAVMOVIE, FAVDIRECTOR);
        userRepository.save(user);

        // Create a presenter that validates success
        ChangePasswordOutputBoundary successPresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData data) {
                assertEquals("Paul", data.getUsername());
            }

            @Override
            public void prepareFailView(String errorMessage) {

            }

            @Override
            public void switchToUserView() {
                // No action needed for this test
            }
        };

        // Create the interactor and execute the use case
        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(userRepository, successPresenter, factory);
        interactor.execute(inputData);

        // Assert the password has been updated in the repository
        assertEquals("newPassword", userRepository.get("Paul").getPassword());
    }
}

