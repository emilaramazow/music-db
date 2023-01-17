package bg.softuni.musicdbapp.repository;

import bg.softuni.musicdbapp.model.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    Optional<ArticleEntity> findTopByOrderByCreatedOnDesc();

}
