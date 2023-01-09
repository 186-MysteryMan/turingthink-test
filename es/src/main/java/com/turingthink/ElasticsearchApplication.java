package com.turingthink;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * @author GongJie Sheng
 * @version v1.0.0
 * @Date 2023-01-09 09:21:22
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class ElasticsearchApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(ElasticsearchApplication.class, args);
        Environment environment = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        String contextPath = environment.getProperty("server.servlet.context-path");
        contextPath = Objects.isNull(contextPath) ? "" : contextPath;
        log.info("\n\t-------------------------------------------------------------\n\t" +
                "本 地 地 址: \thttp://localhost:" + port + contextPath + "/\n\t" +
                "外 部 地 址: \thttp://" + ip + ":" + port + contextPath + "/\n\t" +
                "Swagger-ui地址: http://" + ip + ":" + port + contextPath + "/swagger-ui/\n\t" +
                "Knife4j-ui地址: http://" + ip + ":" + port + contextPath + "/doc.html\n\t" +
                "-------------------------------------------------------------");
    }
}