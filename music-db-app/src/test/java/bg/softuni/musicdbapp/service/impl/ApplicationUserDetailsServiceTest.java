package bg.softuni.musicdbapp.service.impl;

import bg.softuni.musicdbapp.model.entity.UserEntity;
import bg.softuni.musicdbapp.model.entity.UserRoleEntity;
import bg.softuni.musicdbapp.model.enums.UserRoleEnum;
import bg.softuni.musicdbapp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)     //extendWith -> we can use mock annotations in this class
public class ApplicationUserDetailsServiceTest {

    private ApplicationUserDetailsService serviceToTest;

    @Mock
    UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new ApplicationUserDetailsService(mockUserRepository);
    }

    @Test
    public void testUserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class, () -> {
                    serviceToTest.loadUserByUsername("user_does_not_exists");
                }
        );
    }

    @Test
    void testExistingUser() {
        //prepare data
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("emil");
        userEntity.setPassword("qwerty");

        UserRoleEntity roleUser = new UserRoleEntity();
        roleUser.setRole(UserRoleEnum.USER);
        UserRoleEntity roleAdmin = new UserRoleEntity();
        roleAdmin.setRole(UserRoleEnum.ADMIN);

        userEntity.setRoles(List.of(roleUser,roleAdmin));

        //configure mocks
        Mockito.when(mockUserRepository.findByUsername("emil"))
                .thenReturn(Optional.of(userEntity));

        // test
        UserDetails userDetails = serviceToTest.loadUserByUsername("emil");

        Assertions.assertEquals(userEntity.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(2, userDetails.getAuthorities().size());

        List<String> authorities = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Assertions.assertTrue(authorities.contains("ROLE_ADMIN"));
        Assertions.assertTrue(authorities.contains("ROLE_USER"));
    }

}
