package bg.softuni.musicdbapp.service.impl;

import bg.softuni.musicdbapp.model.entity.AlbumEntity;
import bg.softuni.musicdbapp.model.entity.UserEntity;
import bg.softuni.musicdbapp.model.service.AlbumServiceModel;
import bg.softuni.musicdbapp.repository.AlbumRepository;
import bg.softuni.musicdbapp.repository.UserRepository;
import bg.softuni.musicdbapp.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void createAlbum(AlbumServiceModel albumServiceModel) {
        AlbumEntity albumEntity = modelMapper
                .map(albumServiceModel, AlbumEntity.class);

        UserEntity creator = userRepository.findByUsername(albumServiceModel.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Creator " + albumServiceModel.getUsername() + "could not be found!"));

        albumEntity.setUserEntity(creator);

        albumRepository.save(albumEntity);

    }
}
