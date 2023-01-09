package bg.softuni.musicdbapp.web;

import bg.softuni.musicdbapp.model.enums.AlbumGenreEnum;
import bg.softuni.musicdbapp.repository.AlbumRepository;
import bg.softuni.musicdbapp.repository.ArtistRepository;
import bg.softuni.musicdbapp.repository.LogRepository;
import bg.softuni.musicdbapp.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AlbumRestControllerTest {

    private long testAlbumId;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private LogRepository logRepository;

    private AlbumTestData albumTestData;


    @BeforeEach
    public void setUp() {
        albumTestData = new AlbumTestData(artistRepository, userRepository, albumRepository, logRepository);
        albumTestData.init();
        testAlbumId = albumTestData.getTestAlbumId();
    }

    @AfterEach
    public void cleanUpData() {
        albumTestData.cleanUp();
    }

    @Test
    @WithMockUser(value = "emil", roles = {"USER", "ADMIN"})
    public void testFetchAlbums() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/albums/api"))
                .andExpect(status().isOk())
                // we work with JSON API and we test and work with JSON PATH OBJECTS
                .andExpect(jsonPath("[0].name").value("NOTHING ELSE MATTERS"))
                .andExpect(jsonPath("[0].copies").value(500))
                .andExpect(jsonPath("[0].genre").value(AlbumGenreEnum.METAL.name()))
                .andExpect(jsonPath("[1].name").value("Death Magnetic"))
                .andExpect(jsonPath("[1].copies").value(200))
                .andExpect(jsonPath("[1].genre").value(AlbumGenreEnum.METAL.name()));
    }

}
