package com.mytest.billapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.mytest.billapp.config.SecurityConfig;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mytest.billapp"})
@Import({SecurityConfig.class})
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
