package bg.softuni.musicdbapp.service;

import bg.softuni.musicdbapp.model.view.ArtistViewModel;

import java.util.List;

public interface ArtistService {

    void seedArtists();

    List<ArtistViewModel> findAllArtists();

}
