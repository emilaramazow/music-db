package bg.softuni.musicdbapp.service;

import bg.softuni.musicdbapp.model.service.UserRegistrationServiceModel;

public interface UserService {

    void seedUsers();

    void registerAndLoginUser(UserRegistrationServiceModel serviceModel);
}
