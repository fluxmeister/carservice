package com.vektorwebsolutions.carservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class CarServiceApplication extends SpringBootServletInitializer{
	
	
    public static void main( String[] args ) {
        SpringApplication.run(CarServiceApplication.class, args);
    }
}
