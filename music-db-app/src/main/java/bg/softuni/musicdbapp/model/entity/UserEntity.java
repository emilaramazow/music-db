package bg.softuni.musicdbapp.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles = new ArrayList<>();

    public UserEntity() {
    }

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<bg.softuni.musicdbapp.model.entity.UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<bg.softuni.musicdbapp.model.entity.UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
