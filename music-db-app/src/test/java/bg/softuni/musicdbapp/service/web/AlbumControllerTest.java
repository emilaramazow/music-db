package bg.softuni.musicdbapp.service.web;

import bg.softuni.musicdbapp.model.entity.AlbumEntity;
import bg.softuni.musicdbapp.model.entity.ArtistEntity;
import bg.softuni.musicdbapp.model.entity.UserEntity;
import bg.softuni.musicdbapp.model.enums.AlbumGenreEnum;
import bg.softuni.musicdbapp.repository.AlbumRepository;
import bg.softuni.musicdbapp.repository.ArtistRepository;
import bg.softuni.musicdbapp.repository.UserRepository;
import bg.softuni.musicdbapp.web.AlbumController;
import org.junit.jupiter.api.Assertions;
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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
        mockMvc.perform(MockMvcRequestBuilders
                        .get(ALBUM_CONTROLLER_PREFIX + "/details/{id}", testAlbumId))
                .andExpect(status().isOk())
                .andExpect(view().name("details"))
                .andExpect(model().attributeExists("album"));
    }

    @Test
    @WithMockUser(value = "emil", roles = {"USER", "ADMIN"})
    void addAlbum() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ALBUM_CONTROLLER_PREFIX + "/add")
                        .param("name", "test album")
                        .param("genre", "AlbumGenreEnum.METAL.name()")
                        .param("imageURL", "https://upload.wikimedia.org/wikipedia/en/f/f5/Metallica_-_Nothing_Else_Matters_cover.jpg")
                        .param("videoURL", "tAGnKpE4NCI")
                        .param("description", "description test")
                        .param("copies", "12321")
                        .param("price", "10")
                        .param("releaseDate", "1998-05-03")
                        .param("artist", "Metallica")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, albumRepository.count());
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
