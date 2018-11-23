package com.gl.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.gl.demo.handler"))
	      .paths(PathSelectors.ant("/demo2/v2/user/**"))
	      .build()
	      .apiInfo(apiInfo());
	}
	 
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
                .title("GL Microservice Workshop")
                .description("Spring Boot + Spring REST + Swagger + Couchbase")
                .termsOfServiceUrl("http://www.globallogic.com")
                .license("Apache License Version 2.0")
                .version("0.0.1")
                .build();
    }
}
