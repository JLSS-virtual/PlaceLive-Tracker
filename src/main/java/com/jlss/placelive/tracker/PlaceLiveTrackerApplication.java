package com.jlss.placelive.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jlss.placelive.geofencing", "com.jlss.placelive.commonlib"})
public class PlaceLiveTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaceLiveTrackerApplication.class, args);
	}

}
