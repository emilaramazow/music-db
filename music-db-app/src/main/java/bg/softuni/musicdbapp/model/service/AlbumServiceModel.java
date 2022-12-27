package bg.softuni.musicdbapp.model.service;

import bg.softuni.musicdbapp.model.enums.AlbumGenreEnum;

import java.math.BigDecimal;
import java.time.Instant;

public class AlbumServiceModel {

    private String name;
    private String imageURL;
    private String videoURL;
    private String description;
    private Integer copies;
    private BigDecimal price;
    private Instant releaseDate;
    private AlbumGenreEnum genre;
    private String artist;
    private String user;

    public AlbumServiceModel() {
    }

    public String getName() {
        return name;
    }

    public AlbumServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public AlbumServiceModel setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public AlbumServiceModel setVideoURL(String videoURL) {
        this.videoURL = videoURL;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AlbumServiceModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public AlbumServiceModel setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public AlbumGenreEnum getGenre() {
        return genre;
    }

    public AlbumServiceModel setGenre(AlbumGenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public AlbumServiceModel setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public String getUser() {
        return user;
    }

    public AlbumServiceModel setUser(String user) {
        this.user = user;
        return this;
    }
}
