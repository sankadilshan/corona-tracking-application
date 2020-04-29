package com.corona.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TrackerCovid19Application {

	public static void main(String[] args) {
		SpringApplication.run(TrackerCovid19Application.class, args);
	}

}
