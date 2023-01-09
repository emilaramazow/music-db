package bg.softuni.musicdbapp.service.impl;

import bg.softuni.musicdbapp.exceptions.ObjectNotFoundException;
import bg.softuni.musicdbapp.model.entity.AlbumEntity;
import bg.softuni.musicdbapp.model.entity.ArtistEntity;
import bg.softuni.musicdbapp.model.entity.UserEntity;
import bg.softuni.musicdbapp.model.service.AlbumServiceModel;
import bg.softuni.musicdbapp.model.view.AlbumViewModel;
import bg.softuni.musicdbapp.repository.AlbumRepository;
import bg.softuni.musicdbapp.repository.UserRepository;
import bg.softuni.musicdbapp.service.AlbumService;
import bg.softuni.musicdbapp.service.ArtistService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;
    private final ArtistService artistService;
    private final UserRepository userRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository, ModelMapper modelMapper, ArtistService artistService, UserRepository userRepository) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.artistService = artistService;
        this.userRepository = userRepository;
    }

    @Override
    public void createAlbum(AlbumServiceModel albumServiceModel) {
        AlbumEntity albumEntity = modelMapper
                .map(albumServiceModel, AlbumEntity.class);

        UserEntity creator = userRepository.findByUsername(albumServiceModel.getUser())
                .orElseThrow(() -> new IllegalArgumentException("Creator " + albumServiceModel.getUser() + "could not be found!"));

        albumEntity.setUserEntity(creator);

        ArtistEntity artistEntity = artistService.findByName(albumServiceModel.getArtist());
        albumEntity.setArtistEntity(artistEntity);

        albumRepository.save(albumEntity);

    }

    @Override
    public AlbumViewModel findById(Long id) {
        return albumRepository
                .findById(id)
                .map(albumEntity -> {
                    AlbumViewModel albumViewModel = modelMapper
                            .map(albumEntity, AlbumViewModel.class);
                    albumViewModel.setArtist(albumEntity.getArtistEntity().getName());
                    return albumViewModel;
                })
                .orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public AlbumEntity findEntityById(Long albumId) {
        return albumRepository.findById(albumId).orElseThrow(ObjectNotFoundException::new);
    }
}
