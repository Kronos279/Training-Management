package com.TrainingManagement.MentorDetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MentorDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MentorDetailsApplication.class, args);
	}

}
