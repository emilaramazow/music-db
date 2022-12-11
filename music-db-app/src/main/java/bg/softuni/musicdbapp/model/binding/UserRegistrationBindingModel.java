package bg.softuni.musicdbapp.model.binding;

import bg.softuni.musicdbapp.model.validators.FieldMatch;
import jakarta.validation.constraints.*;

@FieldMatch(first = "password", second = "confirmPassword")
public class UserRegistrationBindingModel {


    @NotEmpty
    @Size(min = 3)
    private String fullName;
    @NotEmpty
    @Size(min = 3)
    private String username;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 5, max = 20)
    private String password;
    @NotEmpty
    private String confirmPassword;

    public UserRegistrationBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegistrationBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
