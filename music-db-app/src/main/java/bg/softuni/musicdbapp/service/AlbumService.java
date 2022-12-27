package bg.softuni.musicdbapp.service;

import bg.softuni.musicdbapp.model.service.AlbumServiceModel;
import bg.softuni.musicdbapp.model.view.AlbumViewModel;

public interface AlbumService {

    void createAlbum(AlbumServiceModel albumServiceModel);

    AlbumViewModel findById(Long id);
}
