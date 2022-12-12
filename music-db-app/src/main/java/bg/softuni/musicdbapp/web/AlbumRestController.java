package bg.softuni.musicdbapp.web;

import bg.softuni.musicdbapp.model.binding.AlbumBindingModel;
import bg.softuni.musicdbapp.repository.AlbumRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/albums")
public class AlbumRestController {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;

    public AlbumRestController(AlbumRepository albumRepository, ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api")
    public List<AlbumBindingModel> findAll() {
        return albumRepository
                .findAll()
                .stream()
                .map(ae -> modelMapper.map(ae, AlbumBindingModel.class))
                .collect(Collectors.toList());
    }
}
