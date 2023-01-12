package com.turingthink.rabbit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author GongJie Sheng
 * @version v1.0.0
 * @Date 2023-01-12 13:05:03
 * @description
 */
@Configuration
public class CorsConfiguration {

    /**
     * 处理跨域，临时让所有接口均可跨域请求
     *
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*") //允许跨域的域名，可以用*表示允许任何域名使用
                        .allowedMethods("*") //允许任何方法（post、get等）
                        .allowedHeaders("*") //允许任何请求头
                        .allowCredentials(true) //带上cookie信息
                        .exposedHeaders(HttpHeaders.SET_COOKIE)
                        .maxAge(3600L); //maxAge(3600)表明在3600秒内，不需要再发送预检验请求，可以缓存该结果
            }
        };
    }
}
