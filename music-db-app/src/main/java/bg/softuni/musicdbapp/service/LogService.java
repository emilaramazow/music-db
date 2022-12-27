package bg.softuni.musicdbapp.service;

import bg.softuni.musicdbapp.model.service.LogServiceModel;

import java.util.List;

public interface LogService {
    void createLog(String action, Long albumId);

    List<LogServiceModel> findAllLogs();
}
