package bg.softuni.musicdbapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MusicDbAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicDbAppApplication.class, args);
	}

}
