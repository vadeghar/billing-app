package com.mytest.billapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mytest.billapp"})
@EnableJpaAuditing
public class BillAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillAppApplication.class, args);
	}
}
