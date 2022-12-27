package bg.softuni.musicdbapp.service;

import bg.softuni.musicdbapp.model.entity.UserEntity;
import bg.softuni.musicdbapp.model.service.UserRegistrationServiceModel;

public interface UserService {

    void seedUsers();

    void registerAndLoginUser(UserRegistrationServiceModel serviceModel);

    boolean usernameExists(String username);

    UserEntity findByName(String username);
}
