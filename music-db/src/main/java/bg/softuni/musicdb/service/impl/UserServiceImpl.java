package bg.softuni.musicdb.service.impl;

import bg.softuni.musicdb.model.entity.UserEntity;
import bg.softuni.musicdb.model.entity.UserRoleEntity;
import bg.softuni.musicdb.model.enums.UserRoleEnum;
import bg.softuni.musicdb.repository.UserRepository;
import bg.softuni.musicdb.repository.UserRoleRepository;
import bg.softuni.musicdb.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void seedUsers() {
        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));

            UserEntity admin = new UserEntity()
                    .setName("admin")
                    .setPassword(passwordEncoder.encode("123456"));

            UserEntity user = new UserEntity()
                    .setName("user")
                    .setPassword(passwordEncoder.encode("123456"));

            admin.setRoles(List.of(adminRole, userRole));
            user.setRoles(List.of(userRole));

            userRepository.saveAll(List.of(admin, user));
        }
    }
}