package bg.softuni.musicdbapp.web;

import bg.softuni.musicdbapp.model.binding.AlbumAddBindingModel;
import bg.softuni.musicdbapp.model.service.AlbumServiceModel;
import bg.softuni.musicdbapp.service.AlbumService;
import bg.softuni.musicdbapp.service.ArtistService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public Model addAlbum(Model model) {
       model.addAttribute("artists", artistService.findAllArtists());

        return model;
    }

    @PostMapping("/add")
    public String addAlbum(@RequestBody AlbumAddBindingModel albumAddBindingModel,
                           @AuthenticationPrincipal UserDetails principal) {
        AlbumServiceModel albumServiceModel = modelMapper
                .map(albumAddBindingModel, AlbumServiceModel.class);

        albumServiceModel.setUsername(principal.getUsername());
        albumService.createAlbum(albumServiceModel);

        return "redirect:/home";
    }
}
