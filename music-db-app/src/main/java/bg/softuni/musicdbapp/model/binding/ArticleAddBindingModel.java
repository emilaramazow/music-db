package bg.softuni.musicdbapp.model.binding;

import bg.softuni.musicdbapp.model.enums.AlbumGenreEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class ArticleAddBindingModel {

    @NotEmpty
    @Size(min = 3, max = 20)
    private String title;
    @NotEmpty
    @Size(min = 5)
    private String imageURL;
    @NotNull
    @Size(min = 5)
    private AlbumGenreEnum genre;
    private String content;

    public ArticleAddBindingModel() {
    }

    public String getTitle() {
        return title;
    }

    public ArticleAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ArticleAddBindingModel setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public AlbumGenreEnum getGenre() {
        return genre;
    }

    public ArticleAddBindingModel setGenre(AlbumGenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleAddBindingModel setContent(String content) {
        this.content = content;
        return this;
    }
}
