package bg.softuni.musicdbapp.service.impl;

import bg.softuni.musicdbapp.model.entity.ArticleEntity;
import bg.softuni.musicdbapp.model.entity.UserEntity;
import bg.softuni.musicdbapp.model.enums.AlbumGenreEnum;
import bg.softuni.musicdbapp.model.view.ArticleViewModel;
import bg.softuni.musicdbapp.repository.ArticleRepository;
import bg.softuni.musicdbapp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceImplTest {

    private UserEntity user, user2;
    private ArticleEntity article, article2;

    private ArticleServiceImpl serviceToTest;

    @Mock
    ArticleRepository mockArticleRepository;

    @Mock
    UserRepository mockUserRepository;

    @BeforeEach
    public void init() {
        user = new UserEntity();
        user.setUsername("user1");

        article = new ArticleEntity();
        article.setTitle("first article");
        article.setImageURL("first image");
        article.setGenre(AlbumGenreEnum.METAL);
        article.setContent("first content");
        article.setUserEntity(user);


        user2 = new UserEntity();
        user2.setUsername("user2");

        article2 = new ArticleEntity();
        article2.setTitle("second article");
        article2.setImageURL("second image");
        article2.setGenre(AlbumGenreEnum.METAL);
        article2.setContent("second content");
        article2.setUserEntity(user2);

        serviceToTest = new ArticleServiceImpl(new ModelMapper(), mockArticleRepository, mockUserRepository);
    }

    @Test
    public void testFindAll() {
        when(mockArticleRepository.findAll()).thenReturn(List.of(article, article2));

        List<ArticleViewModel> allModels = serviceToTest.findAllArticles();

        Assertions.assertEquals(2, allModels.size());

        ArticleViewModel model1 = allModels.get(0);
        ArticleViewModel model2 = allModels.get(1);

        Assertions.assertEquals(article.getTitle(), model1.getTitle());
        Assertions.assertEquals(article.getImageURL(), model1.getImageURL());
        Assertions.assertEquals(article.getGenre(), model1.getGenre());
        Assertions.assertEquals(article.getContent(), model1.getContent());
        Assertions.assertEquals(user.getUsername(), model1.getAuthor());

        Assertions.assertEquals(article2.getTitle(), model2.getTitle());
        Assertions.assertEquals(article2.getImageURL(), model2.getImageURL());
        Assertions.assertEquals(article2.getGenre(), model2.getGenre());
        Assertions.assertEquals(article2.getContent(), model2.getContent());
        Assertions.assertEquals(user2.getUsername(), model2.getAuthor());

    }

    @Test
    public void testLatestArticle() {
        when(mockArticleRepository.findTopByOrderByCreatedOnDesc())
                .thenReturn(Optional.of(article));

        Optional<ArticleViewModel> articleViewOpt = serviceToTest.findLatestArticle();

        Assertions.assertTrue(articleViewOpt.isPresent());
        ArticleViewModel actualModel = articleViewOpt.get();

        Assertions.assertEquals(article.getTitle(), actualModel.getTitle());
        Assertions.assertEquals(article.getContent(), actualModel.getContent());
        // and so on...
    }

    @Test
    public void testLatestArticleNotFound() {
        when(mockArticleRepository.findTopByOrderByCreatedOnDesc())
                .thenReturn(Optional.empty());

        Optional<ArticleViewModel> articleViewOpt = serviceToTest.findLatestArticle();

        Assertions.assertTrue(articleViewOpt.isEmpty());
    }
}
