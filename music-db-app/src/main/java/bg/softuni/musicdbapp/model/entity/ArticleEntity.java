package bg.softuni.musicdbapp.model.entity;

import bg.softuni.musicdbapp.model.enums.AlbumGenreEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "articles")
public class ArticleEntity extends BaseEntity {

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String imageURL;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AlbumGenreEnum genre;
    @Column(nullable = false)
    private String content;

    @ManyToOne
    private UserEntity userEntity;

    public ArticleEntity() {
    }

    public String getTitle() {
        return title;
    }

    public ArticleEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ArticleEntity setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public AlbumGenreEnum getGenre() {
        return genre;
    }

    public ArticleEntity setGenre(AlbumGenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public ArticleEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
