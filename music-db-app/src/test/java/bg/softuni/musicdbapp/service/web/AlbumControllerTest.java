package bg.softuni.musicdbapp.service.web;

import bg.softuni.musicdbapp.model.entity.AlbumEntity;
import bg.softuni.musicdbapp.model.entity.ArtistEntity;
import bg.softuni.musicdbapp.model.entity.UserEntity;
import bg.softuni.musicdbapp.model.enums.AlbumGenreEnum;
import bg.softuni.musicdbapp.repository.AlbumRepository;
import bg.softuni.musicdbapp.repository.ArtistRepository;
import bg.softuni.musicdbapp.repository.UserRepository;
import bg.softuni.musicdbapp.web.AlbumController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AlbumControllerTest {

    private static final String ALBUM_CONTROLLER_PREFIX = "/albums";

    private long testAlbumId;

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        this.init();
    }

    @Test
    @WithMockUser(value = "emil", roles = {"USER", "ADMIN"})
    void shouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                        get(ALBUM_CONTROLLER_PREFIX + "/details/{id}", testAlbumId))
                .andExpect(status().isOk())
                .andExpect(view().name("details"))
                .andExpect(model().attributeExists("album"));
    }

    private void init() {
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName("Metallica");
        artistEntity.setCareerInformation("Some info about Metallica");
        artistRepository.save(artistEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("emil");
        userEntity.setPassword("qwerty");
        userEntity.setFullName("emil emilov");
        userRepository.save(userEntity);

        AlbumEntity albumEntity = new AlbumEntity();
        albumEntity
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

        albumRepository.save(albumEntity);
        testAlbumId = albumEntity.getId();
    }
}
