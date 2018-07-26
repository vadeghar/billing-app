package com.mytest.billapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mytest.billapp"})
@EnableAutoConfiguration
@EnableJpaAuditing
public class BillAppApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BillAppApplication.class);
    }
    
	public static void main(String[] args) {
		SpringApplication.run(BillAppApplication.class, args);
	}
}


//https://github.com/mmeany/spring-boot-web-mvc-tiles3-security