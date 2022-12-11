package bg.softuni.musicdbapp.repository;

import bg.softuni.musicdbapp.model.entity.UserRoleEntity;
import bg.softuni.musicdbapp.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByRole(UserRoleEnum role);

}
