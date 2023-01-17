package bg.softuni.musicdbapp.model.service;

import bg.softuni.musicdbapp.model.enums.AlbumGenreEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ArticleServiceModel {

    private String title;
    private String imageURL;
    private AlbumGenreEnum genre;
    private String content;
    private String userName;

    public ArticleServiceModel() {
    }

    public String getTitle() {
        return title;
    }

    public ArticleServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ArticleServiceModel setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public AlbumGenreEnum getGenre() {
        return genre;
    }

    public ArticleServiceModel setGenre(AlbumGenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleServiceModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ArticleServiceModel setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
