package interface_adapter.user_repository;

import use_case.saveuser.SaveUserInputBoundary;
import use_case.saveuser.SaveUserInputData;

public class SaveUserController {
    private final SaveUserInputBoundary interactor;

    public SaveUserController(SaveUserInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void saveUser(String name, String password,String favMovie, String favDirector) {
        SaveUserInputData inputData = new SaveUserInputData(name, password, favMovie, favDirector);
        interactor.execute(inputData);
    }
}
