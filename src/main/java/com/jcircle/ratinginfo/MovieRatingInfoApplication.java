package com.jcircle.ratinginfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MovieRatingInfoApplication {

	/**
	 * Reactive Web Service / Asyn Calls
	 * @return
	 */
	@Bean
   public WebClient.Builder getWebClient() {

		return  WebClient.builder();

   }


	public static void main(String[] args) {
		SpringApplication.run(MovieRatingInfoApplication.class, args);
	}

}
