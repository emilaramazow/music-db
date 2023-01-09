package bg.softuni.musicdbapp.service;

import bg.softuni.musicdbapp.model.view.ArticleViewModel;

import java.util.List;

public interface ArticleService {

    List<ArticleViewModel> findAllArticles();
}
