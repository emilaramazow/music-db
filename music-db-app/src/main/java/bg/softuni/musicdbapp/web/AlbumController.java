package bg.softuni.musicdbapp.web;

import bg.softuni.musicdbapp.model.binding.AlbumAddBindingModel;
import bg.softuni.musicdbapp.model.service.AlbumServiceModel;
import bg.softuni.musicdbapp.service.AlbumService;
import bg.softuni.musicdbapp.service.ArtistService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.ZoneId;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final ModelMapper modelMapper;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public AlbumController(ModelMapper modelMapper, AlbumService albumService, ArtistService artistService) {
        this.modelMapper = modelMapper;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @ModelAttribute("albumAddBindingModel")
    public AlbumAddBindingModel createAlbumAddBindingModel() {
        return new AlbumAddBindingModel();
    }

    @GetMapping("/add")
    public String addAlbum(Model model) {
        model.addAttribute("artists", artistService.findAllArtists());

        return "add-album";
    }

    @PostMapping("/add")
    public String addAlbum(@Valid AlbumAddBindingModel albumAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails principal) {

        // we take the username of the logged user (@AuthenticationPrincipal UserDetails principal) - injected from the constructor

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("albumAddBindingModel", albumAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.albumAddBindingModel", bindingResult);

            return "redirect:add";
        }

        AlbumServiceModel albumServiceModel = modelMapper
                .map(albumAddBindingModel, AlbumServiceModel.class);

        albumServiceModel.setUser(principal.getUsername());

        albumServiceModel.setReleaseDate(albumAddBindingModel
                .getReleaseDate()
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant());

        albumService.createAlbum(albumServiceModel);

        return "redirect:/home";
    }
}
