package com.stackroute.matchrecommendationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MatchRecommendationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchRecommendationServiceApplication.class, args);
	}

}

