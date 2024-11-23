package use_case.saveuser;


import entity.User;
import entity.UserFactory;
import interface_adapter.user_repository.UserRepository;

import java.io.IOException;

public class SaveUserInteractor implements SaveUserInputBoundary{
    private final UserRepository repository;
    private final UserFactory userFactory;

    public SaveUserInteractor(UserRepository repository, UserFactory userFactory) {
        this.repository = repository;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SaveUserInputData saveUserInputData) {
        User user = userFactory.create(saveUserInputData.getName(), saveUserInputData.getPassword(),saveUserInputData.getFavMovie(),saveUserInputData.getFavDirector());
        try {
            repository.save(user);
        } catch (IOException e) {
            // Handle error, e.g., log it or throw an application-specific error
            System.err.println("Failed to save user data: " + e.getMessage());
        }
    }
}
