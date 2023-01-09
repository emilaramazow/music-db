package bg.softuni.musicdbapp.service.impl;

import bg.softuni.musicdbapp.service.CarouselService;
import bg.softuni.musicdbapp.service.ImageShuffler;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    This class is Scheduler class for carousel images (change images after a minute) :
*/

@Service
public class CarouselServiceImpl implements CarouselService {

    private final Logger LOGGER = LoggerFactory.getLogger(CarouselServiceImpl.class);

    private final List<String> images = new ArrayList<>();
    private final ImageShuffler imageShuffler;

    public CarouselServiceImpl(@Value("${carousel.images}") List<String> images, ImageShuffler imageShuffler) {
        this.imageShuffler = imageShuffler;
        this.images.addAll(images);
    }
    // check if we have enough images for the carousel
    @PostConstruct
    public void afterInitialize() {
        if (images.size() < 3) {
            throw new IllegalArgumentException("Sorry, but you must configure at least 3 images!");
        }
    }

    @Override
    public String firstImage() {
        return images.get(0);
    }

    @Override
    public String secondImage() {
        return images.get(1);
    }

    @Override
    public String thirdImage() {
        return images.get(2);
    }


    /*
        CRON JOB for rotate after 1 minute :
     */

    @Scheduled(cron = "${carousel.refresh-cron}")
    public void refresh() {
        LOGGER.info("Shuffling images...");
        imageShuffler.shuffle(images);
    }

}
