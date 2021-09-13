package com.Hanium.CarCamping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class
CarCampingApplication {

	public static void main(String[] args) {
		System.out.println(String.format("배포일시: %S", LocalDateTime.now()));
		SpringApplication.run(CarCampingApplication.class, args);

	}

}
