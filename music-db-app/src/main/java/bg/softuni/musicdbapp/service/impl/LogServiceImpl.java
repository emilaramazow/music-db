package bg.softuni.musicdbapp.service.impl;

import bg.softuni.musicdbapp.model.entity.AlbumEntity;
import bg.softuni.musicdbapp.model.entity.LogEntity;
import bg.softuni.musicdbapp.model.entity.UserEntity;
import bg.softuni.musicdbapp.repository.LogRepository;
import bg.softuni.musicdbapp.service.AlbumService;
import bg.softuni.musicdbapp.service.LogService;
import bg.softuni.musicdbapp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final AlbumService albumService;
    private final UserService userService;

    public LogServiceImpl(LogRepository logRepository, AlbumService albumService, UserService userService) {
        this.logRepository = logRepository;
        this.albumService = albumService;
        this.userService = userService;
    }

    @Override
    public void createLog(String action, Long albumId) {
        AlbumEntity albumEntity = albumService
                .findEntityById(albumId);

        // take the user from authentication (level service). if we want to take it level controller - we use Principal
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = authentication.getName();
        UserEntity userEntity = userService.findByName(username);

        LogEntity logEntity = new LogEntity()
                .setAlbumEntity(albumEntity)
                .setUserEntity(userEntity)
                .setAction(action)
                .setDateTime(LocalDateTime.now());

        logRepository.save(logEntity);
    }
}
