package bg.softuni.musicdbapp.web;

import bg.softuni.musicdbapp.model.entity.AlbumEntity;
import bg.softuni.musicdbapp.model.entity.ArtistEntity;
import bg.softuni.musicdbapp.model.entity.UserEntity;
import bg.softuni.musicdbapp.model.enums.AlbumGenreEnum;
import bg.softuni.musicdbapp.repository.AlbumRepository;
import bg.softuni.musicdbapp.repository.ArtistRepository;
import bg.softuni.musicdbapp.repository.LogRepository;
import bg.softuni.musicdbapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

public class AlbumTestData {

    private long testAlbumId;

    private ArtistRepository artistRepository;
    private UserRepository userRepository;
    private AlbumRepository albumRepository;
    private LogRepository logRepository;

    public AlbumTestData(ArtistRepository artistRepository, UserRepository userRepository, AlbumRepository albumRepository, LogRepository logRepository) {
        this.artistRepository = artistRepository;
        this.userRepository = userRepository;
        this.albumRepository = albumRepository;
        this.logRepository = logRepository;
    }

    public void init() {
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName("Metallica");
        artistEntity.setCareerInformation("Some info about Metallica");
        artistRepository.save(artistEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("emil");
        userEntity.setPassword("qwerty");
        userEntity.setFullName("emil emilov");
        userRepository.save(userEntity);

        AlbumEntity nothingElseMatter = new AlbumEntity();
        nothingElseMatter
                .setName("NOTHING ELSE MATTERS")
                .setImageURL("https://upload.wikimedia.org/wikipedia/en/f/f5/Metallica_-_Nothing_Else_Matters_cover.jpg")
                .setVideoURL("https://www.youtube.com/watch?v=tAGnKpE4NCI")
                .setDescription("DESCRIPTION")
                .setCopies(500)
                .setPrice(BigDecimal.TEN)
                .setReleaseDate(LocalDate.of(1998, 5, 3).atStartOfDay(ZoneId.systemDefault()).toInstant())
                .setGenre(AlbumGenreEnum.METAL)
                .setArtistEntity(artistEntity)
                .setUserEntity(userEntity);

        albumRepository.save(nothingElseMatter);

        AlbumEntity deathMagnetic = new AlbumEntity();
        deathMagnetic
                .setName("Death Magnetic")
                .setImageURL("https://upload.wikimedia.org/wikipedia/en/f/f5/Metallica_-_Nothing_Else_Matters_cover.jpg")
                .setVideoURL("https://www.youtube.com/watch?v=tAGnKpE4NCI")
                .setDescription("Description")
                .setCopies(200)
                .setPrice(BigDecimal.TEN)
                .setReleaseDate(LocalDate.of(2008, 1, 6).atStartOfDay(ZoneId.systemDefault()).toInstant())
                .setGenre(AlbumGenreEnum.METAL)
                .setArtistEntity(artistEntity)
                .setUserEntity(userEntity);

        albumRepository.save(deathMagnetic);

        testAlbumId = nothingElseMatter.getId();
    }

    void cleanUp() {
        logRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        userRepository.deleteAll();
    }

    public long getTestAlbumId() {
        return testAlbumId;
    }
}
