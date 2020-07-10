package com.anuj.springrestservice.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Swagger URL --> http://localhost:8080/swagger-ui.html#/
// Have enhanced the swagger documentation with custom annotations
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/*private static final Contact DEFAULT_CONTACT = new Contact("Anuj Mehra","","mehra.anuj1712@gmail.com");
	
	private static final ApiInfo DEFAULT_API_INFO = new  ApiInfo(
			"Anuj's Api Title",
			"Anuj's Api Documentation",
			"1.0",
			"urn:tos",
			DEFAULT_CONTACT,
			"Apache 2.0",
			"http://www.apache.org/licenses/LICENSE-2.0", null);
	*/
	// Bean -Docket
	// Swagger2
	// All the paths
	// All the API's
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2);
				//.apiInfo(DEFAULT_API_INFO);
	}
}
