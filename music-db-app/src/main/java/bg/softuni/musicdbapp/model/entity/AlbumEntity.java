package bg.softuni.musicdbapp.model.entity;

import bg.softuni.musicdbapp.model.enums.AlbumGenreEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "albums")
public class AlbumEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, name = "image_url")
    private String imageURL;
    @Column(name = "video_url")
    private String videoURL;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer copies;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Instant releaseDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlbumGenreEnum genre;

    @ManyToOne
    private ArtistEntity artistEntity;
    @ManyToOne
    private UserEntity userEntity;

    public AlbumEntity() {
    }

    public String getName() {
        return name;
    }

    public AlbumEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public AlbumEntity setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public AlbumEntity setVideoURL(String videoURL) {
        this.videoURL = videoURL;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AlbumEntity setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public AlbumEntity setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public AlbumGenreEnum getGenre() {
        return genre;
    }

    public AlbumEntity setGenre(AlbumGenreEnum genre) {
        this.genre = genre;
        return this;
    }


    public ArtistEntity getArtistEntity() {
        return artistEntity;
    }

    public AlbumEntity setArtistEntity(ArtistEntity artistEntity) {
        this.artistEntity = artistEntity;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public AlbumEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    @Override
    public String toString() {
        return "AlbumEntity{" +
                "name='" + name + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", videoURL='" + videoURL + '\'' +
                ", description='" + description + '\'' +
                ", copies=" + copies +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                ", genre=" + genre +
                ", artist=" + artistEntity +
                ", user=" + userEntity +
                '}';
    }
}
