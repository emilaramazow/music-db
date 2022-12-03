package bg.softuni.musicdb.service.impl;

import bg.softuni.musicdb.model.entity.UserEntity;
import bg.softuni.musicdb.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " was not found"));


        return mapToUserDetails(userEntity);
    }

    // map user entity to spring representation
    private UserDetails mapToUserDetails(UserEntity userEntity) {
        List<GrantedAuthority> authorities =
                userEntity.getRoles()
                        .stream()
                        .map(r -> new SimpleGrantedAuthority(r.getRole().name()))
                        .collect(Collectors.toList());

        return new User(
                userEntity.getName(),
                userEntity.getPassword(),
                authorities
        );

    }
}
