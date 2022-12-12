package bg.softuni.musicdbapp.repository;

import bg.softuni.musicdbapp.model.entity.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {
}
