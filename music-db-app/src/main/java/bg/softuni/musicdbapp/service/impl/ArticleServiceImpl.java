package bg.softuni.musicdbapp.service.impl;

import bg.softuni.musicdbapp.model.entity.ArticleEntity;
import bg.softuni.musicdbapp.model.entity.UserEntity;
import bg.softuni.musicdbapp.model.service.ArticleServiceModel;
import bg.softuni.musicdbapp.model.view.ArticleViewModel;
import bg.softuni.musicdbapp.repository.ArticleRepository;
import bg.softuni.musicdbapp.repository.UserRepository;
import bg.softuni.musicdbapp.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleServiceImpl(ModelMapper modelMapper, ArticleRepository articleRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
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

    @Override
    public void createArticle(ArticleServiceModel articleServiceModel) {
        ArticleEntity articleEntity = modelMapper.map(articleServiceModel, ArticleEntity.class);

        UserEntity creator = userRepository.findByUsername(articleServiceModel.getUserName())
                .orElseThrow(() -> new IllegalArgumentException("Creator " + articleServiceModel.getUserName() + " was not found"));

        articleEntity.setUserEntity(creator);

        articleRepository.save(articleEntity);
    }
}
