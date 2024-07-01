package com.muates.postservice;

import com.muates.postservice.init.StreamInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = "com.muates")
@RequiredArgsConstructor
public class PostServiceApplication implements CommandLineRunner {

    private final StreamInitializer streamInitializer;

    public static void main(String[] args) {
        SpringApplication.run(PostServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        streamInitializer.init();
    }
}
