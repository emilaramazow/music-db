package bg.softuni.musicdbapp.model.view;

import bg.softuni.musicdbapp.model.enums.AlbumGenreEnum;

import java.math.BigDecimal;
import java.time.Instant;

public class AlbumViewModel {

    private String name;
    private String imageURL;
    private String videoURL;
    private String description;
    private Integer copies;
    private BigDecimal price;
    private Instant releaseDate;
    private AlbumGenreEnum genre;
    private String artist;

    public AlbumViewModel() {
    }

    public String getName() {
        return name;
    }

    public AlbumViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public AlbumViewModel setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public AlbumViewModel setVideoURL(String videoURL) {
        this.videoURL = videoURL;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AlbumViewModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public AlbumViewModel setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public AlbumGenreEnum getGenre() {
        return genre;
    }

    public AlbumViewModel setGenre(AlbumGenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public AlbumViewModel setArtist(String artist) {
        this.artist = artist;
        return this;
    }
}
