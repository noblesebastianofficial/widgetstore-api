package com.widgetstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * Swagger Configuration file
 * @author Noble Sebastian
 * @version 1.0
 */
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				" Widget Store API",
				"This shows the various APIs for the Widget Store Application",
				"v.1.0.0",
				"",
				new Contact("Noble Sebastian", "//", "noblesebastiank@gmail.com"),
				"",
				"",
				Collections.emptyList()
		);
	}
}
