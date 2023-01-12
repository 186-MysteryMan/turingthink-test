package com.turingthink.rabbit.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version v0.0.0
 * @author: fight2048
 * @e-mail: fight2048@outlook.com
 * @blog: https://github.com/fight2048
 * @time: 2019-01-08 0008 下午 11:17
 * @description:
 */
@Configuration
@EnableOpenApi
@Profile({"dev", "test"})
public class SwaggerConfiguration {

    @Value("${swagger.enable}")
    private Boolean enable;

    @Bean
    public Docket adminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(new ApiInfoBuilder()
                        .title("mq api")
                        .description("mq api")
                        .termsOfServiceUrl("http://www.turingthink.com/")
                        .contact(new Contact("1", "1", "1"))
                        .version("0.0.1")
                        .build()
                )
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .groupName("mq接口");
    }
}
