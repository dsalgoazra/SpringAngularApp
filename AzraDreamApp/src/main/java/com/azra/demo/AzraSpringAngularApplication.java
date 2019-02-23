package com.azra.demo;

import com.azra.demo.config.JmsProperties;
import com.azra.demo.exclude.WebSocketConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@EnableCaching
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSocketConfig.class))

public class AzraSpringAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzraSpringAngularApplication.class, args);
	}

	@Bean
	public JmsProperties props(){
		return new JmsProperties();
	}

}

