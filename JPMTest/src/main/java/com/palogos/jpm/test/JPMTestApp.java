package com.palogos.jpm.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class JPMTestApp implements CommandLineRunner {

	public void run(String... args) throws Exception {
		System.out.println("JPMTEst");

	}

	public static void main(String[] args) {
		SpringApplication.run(JPMTestApp.class, args);

	}

}
