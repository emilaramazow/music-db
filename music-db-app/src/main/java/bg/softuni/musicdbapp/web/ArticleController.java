package bg.softuni.musicdbapp.web;

import bg.softuni.musicdbapp.model.binding.ArticleAddBindingModel;
import bg.softuni.musicdbapp.model.service.ArticleServiceModel;
import bg.softuni.musicdbapp.model.view.ArticleViewModel;
import bg.softuni.musicdbapp.repository.ArticleRepository;
import bg.softuni.musicdbapp.service.ArticleService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    public ArticleController(ModelMapper modelMapper, ArticleRepository articleRepository, ArticleService articleService) {
        this.modelMapper = modelMapper;
        this.articleRepository = articleRepository;
        this.articleService = articleService;
    }

    @ModelAttribute("articleAddBindingModel")
    public ArticleAddBindingModel createArticleAddBindingModel() {
        return new ArticleAddBindingModel();
    }

    @GetMapping("/all")
    public String getAllArticles(Model model) {
        Optional<ArticleViewModel> articleOpt = articleService.findLatestArticle();

        if (articleOpt.isPresent()) {
            model.addAttribute("latestArticle", articleOpt.get());
        }

        return "all-articles";
    }

    @GetMapping("/add")
    public String addArticle() {
        return "add-article";
    }

    @PostMapping("/add")
    public String addArticle(@Valid ArticleAddBindingModel articleAddBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              @AuthenticationPrincipal UserDetails principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("articleAddBindingModel", articleAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.articleAddBindingModel", bindingResult);

            return "redirect:/articles/add";
        }

        ArticleServiceModel articleServiceModel = modelMapper.map(articleAddBindingModel, ArticleServiceModel.class);

        articleServiceModel.setUserName(principal.getUsername());

        articleService.createArticle(articleServiceModel);

        return "redirect:/articles/all";
    }

}
