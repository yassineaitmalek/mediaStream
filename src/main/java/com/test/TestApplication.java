package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.config.LoggingListener;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(TestApplication.class);
        application.addListeners(new LoggingListener());
        application.run(args);

    }

}
