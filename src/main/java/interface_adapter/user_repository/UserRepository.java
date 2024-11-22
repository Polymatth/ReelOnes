package interface_adapter.user_repository;

import entity.User;

import java.io.IOException;

public interface UserRepository{

        void save(User user) throws IOException;
}
