package bg.softuni.musicdbapp.config;

import bg.softuni.musicdbapp.repository.UserRepository;
import bg.softuni.musicdbapp.service.impl.ApplicationUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class ApplicationSecurityConfiguration {

    // 1. Password Encoder
    // 2. Security Filter Chain
    // 3. User Details Service



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/js/**", "/css/**", "/images/**").permitAll()
                .requestMatchers("/", "/users/login", "/users/register", "/home").permitAll()
                .requestMatchers("/**").hasRole("ADMIN")
//                    .requestMatchers("/users/profile").authenticated()
//                    .requestMatchers("/admin").hasRole("ADMIN")
//                    .requestMatchers("/user").hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/users/login").permitAll()
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/home")
                .failureForwardUrl("/users/login-error")
                .and()
                .logout()
                .logoutUrl("/users/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/home");


        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new ApplicationUserDetailsService(userRepository);
    }

}