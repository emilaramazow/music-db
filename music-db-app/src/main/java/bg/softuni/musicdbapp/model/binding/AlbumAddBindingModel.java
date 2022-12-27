package bg.softuni.musicdbapp.model.binding;

import bg.softuni.musicdbapp.model.enums.AlbumGenreEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumAddBindingModel {

    @NotNull
    private AlbumGenreEnum genre;
    @Size(min =  4, max = 20)
    private String name;
    @Size(min =  5)
    private String imageURL;
    private String videoURL;
    @Size(min =  4)
    private String description;
    @Min(0)
    private Integer copies;
    @DecimalMin("0")
    private BigDecimal price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private String artist;

    public AlbumGenreEnum getGenre() {
        return genre;
    }

    public AlbumAddBindingModel setGenre(AlbumGenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlbumAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public AlbumAddBindingModel setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public AlbumAddBindingModel setVideoURL(String videoURL) {
        this.videoURL = videoURL;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AlbumAddBindingModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AlbumAddBindingModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public AlbumAddBindingModel setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    @Override
    public String toString() {
        return "AlbumAddBindingModel{" +
                "genre=" + genre +
                ", name='" + name + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", videoURL='" + videoURL + '\'' +
                ", description='" + description + '\'' +
                ", copies=" + copies +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
