package com.parcel_loop.parcel_loop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ParcelLoopApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ParcelLoopApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner runJob(CronJobMockEnv cronJobMockEnv) {
	// 	return args -> {
	// 		cronJobMockEnv.injectUserWithReturns();
	// 		System.exit(0);
	// 	};
	// }
}
