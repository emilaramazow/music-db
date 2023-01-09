package bg.softuni.musicdbapp.model.view;

import bg.softuni.musicdbapp.model.enums.AlbumGenreEnum;


public class ArticleViewModel {

    private String title;
    private String imageURL;
    private AlbumGenreEnum genre;
    private String content;
    private String author;

    public ArticleViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public ArticleViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ArticleViewModel setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public AlbumGenreEnum getGenre() {
        return genre;
    }

    public ArticleViewModel setGenre(AlbumGenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleViewModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public ArticleViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }
}
