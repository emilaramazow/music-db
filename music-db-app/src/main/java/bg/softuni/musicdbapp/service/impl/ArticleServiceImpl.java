package bg.softuni.musicdbapp.service.impl;

import bg.softuni.musicdbapp.model.view.ArticleViewModel;
import bg.softuni.musicdbapp.repository.ArticleRepository;
import bg.softuni.musicdbapp.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ModelMapper modelMapper, ArticleRepository articleRepository) {
        this.modelMapper = modelMapper;
        this.articleRepository = articleRepository;
    }


    @Override
    public List<ArticleViewModel> findAllArticles() {
        return articleRepository
                .findAll()
                .stream()
                .map(article -> {
                    ArticleViewModel articleViewModel = modelMapper.map(article, ArticleViewModel.class);
                    articleViewModel.setAuthor(article.getUserEntity().getUsername());
                    return articleViewModel;
                })
                .collect(Collectors.toList());
    }
}
