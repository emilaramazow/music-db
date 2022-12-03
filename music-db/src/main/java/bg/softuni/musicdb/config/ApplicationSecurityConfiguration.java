package bg.softuni.musicdb.config;

import bg.softuni.musicdb.repository.UserRepository;
import bg.softuni.musicdb.service.ApplicationUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationSecurityConfiguration {

    // 1. Password Encoder
    // 2. Security Filter Chain
    // 3. User Details Service

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .requestMatchers("/js/**", "/css/**", "/img/**").permitAll()
                    .requestMatchers("/", "/users/login", "/users/register").permitAll()
                    .requestMatchers("/**").authenticated()
                    .requestMatchers("/users/profile").authenticated()
                    .requestMatchers("/admin").hasRole("ADMIN")
                    .requestMatchers("/user").hasRole("USER")
                .and()
                    .formLogin()
                        .loginPage("/users/login").permitAll()
                        .usernameParameter("username")
                        .passwordParameter("password")
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