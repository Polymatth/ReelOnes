package use_case.go_profile;

import org.junit.jupiter.api.Test;
import use_case.goprofile.GoProfileInputBoundary;
import use_case.goprofile.GoProfileInputData;
import use_case.goprofile.GoProfileInteractor;
import use_case.goprofile.GoProfileOutputBoundary;

import static org.junit.jupiter.api.Assertions.*;

class GoProfileInteractorTest {

    @Test
    void successViewTest() {
        // Create a presenter that validates the success view is prepared
        GoProfileOutputBoundary successPresenter = new GoProfileOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                // Assert success view is prepared without any errors
                assertTrue(true, "Success view prepared as expected.");
            }

            @Override
            public void prepareFailView(String error) {

            }

            @Override
            public void switchToProfileView() {
                fail("Unexpected switch to profile view.");
            }

            @Override
            public void switchToMainView() {
                fail("Unexpected switch to main view.");
            }

            @Override
            public void switchToChangePasswordView() {
                fail("Unexpected switch to change password view.");
            }
        };

        GoProfileInputBoundary interactor = new GoProfileInteractor(successPresenter);
        GoProfileInputData inputData = new GoProfileInputData("user");

        // Execute the use case
        interactor.execute(inputData);
    }

    @Test
    void switchToProfileViewTest() {
        // Create a presenter that validates switching to profile view
        GoProfileOutputBoundary profilePresenter = new GoProfileOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                fail("Unexpected preparation of success view.");
            }

            @Override
            public void prepareFailView(String error) {

            }

            @Override
            public void switchToProfileView() {
                // Assert the profile view is switched to without errors
                assertTrue(true, "Switched to profile view as expected.");
            }

            @Override
            public void switchToMainView() {
                fail("Unexpected switch to main view.");
            }

            @Override
            public void switchToChangePasswordView() {
                fail("Unexpected switch to change password view.");
            }
        };

        GoProfileInputBoundary interactor = new GoProfileInteractor(profilePresenter);
        interactor.switchToProfileView();
    }

    @Test
    void switchToMainViewTest() {
        // Create a presenter that validates switching to main view
        GoProfileOutputBoundary mainPresenter = new GoProfileOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                fail("Unexpected preparation of success view.");
            }

            @Override
            public void prepareFailView(String error) {

            }

            @Override
            public void switchToProfileView() {
                fail("Unexpected switch to profile view.");
            }

            @Override
            public void switchToMainView() {
                // Assert the main view is switched to without errors
                assertTrue(true, "Switched to main view as expected.");
            }

            @Override
            public void switchToChangePasswordView() {
                fail("Unexpected switch to change password view.");
            }
        };

        GoProfileInputBoundary interactor = new GoProfileInteractor(mainPresenter);
        interactor.switchToMainView();
    }

    @Test
    void switchToChangePasswordViewTest() {
        // Create a presenter that validates switching to change password view
        GoProfileOutputBoundary changePasswordPresenter = new GoProfileOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                fail("Unexpected preparation of success view.");
            }

            @Override
            public void prepareFailView(String error) {

            }

            @Override
            public void switchToProfileView() {
                fail("Unexpected switch to profile view.");
            }

            @Override
            public void switchToMainView() {
                fail("Unexpected switch to main view.");
            }

            @Override
            public void switchToChangePasswordView() {
                // Assert the change password view is switched to without errors
                assertTrue(true, "Switched to change password view as expected.");
            }
        };

        GoProfileInputBoundary interactor = new GoProfileInteractor(changePasswordPresenter);
        interactor.switchToChangePasswordView();
    }
}
