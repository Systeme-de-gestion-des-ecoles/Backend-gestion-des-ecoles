package com.uds.project.service_notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ServiceNotificationApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();

        System.out.println("Loaded DB_USERNAME: " + dotenv.get("DB_USERNAME"));

        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		SpringApplication.run(ServiceNotificationApplication.class, args);
	}

}
