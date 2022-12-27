package bg.softuni.musicdbapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class LogEntity extends BaseEntity {

    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private AlbumEntity albumEntity;
    @Column(name = "action", nullable = false)
    private String action;
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    public LogEntity() {
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public LogEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public AlbumEntity getAlbumEntity() {
        return albumEntity;
    }

    public LogEntity setAlbumEntity(AlbumEntity albumEntity) {
        this.albumEntity = albumEntity;
        return this;
    }

    public String getAction() {
        return action;
    }

    public LogEntity setAction(String action) {
        this.action = action;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LogEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
