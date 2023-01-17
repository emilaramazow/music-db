package bg.softuni.musicdbapp.service;

import bg.softuni.musicdbapp.model.service.ArticleServiceModel;
import bg.softuni.musicdbapp.model.view.ArticleViewModel;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    Optional<ArticleViewModel> findLatestArticle();

    List<ArticleViewModel> findAllArticles();

    void createArticle(ArticleServiceModel articleServiceModel);
}
