package com.cognixia.jump.config;
/*
 * Access through postman: http://localhost:8080/v2/api-docs
 * 
 * Access through browser: http://localhost:8080/swagger-ui.html
 * 
 * */

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	// method for deciding with apis to document
	
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis( RequestHandlerSelectors.withClassAnnotation(RestController.class) )
				.paths( PathSelectors.any() )
				//.paths( PathSelectors.ant("/api/car/**") )
				//.paths( PathSelectors.regex("/api.*delete.*") )
				.build()
				.apiInfo( apiDetails() );
		
	}
	
	private ApiInfo apiDetails() {
		
		return new ApiInfo(
				"Health Care Enrollment API", 
				"Open source API for obtain/updating enrollees and dependents within a Health Care System.", 
				"1.0", 
				"Free to use", // add terms of use info here or just url to link to terms of use
				new Contact("Haley", "http://github.com/live-git-haley", "hhowell@dons.usfca.edu"), 
				"API License", 
				"http://github.com/live-git-haley", 
				Collections.emptyList()
				);
		
	}

}


