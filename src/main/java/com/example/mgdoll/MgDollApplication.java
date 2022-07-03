package com.example.mgdoll;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class MgDollApplication{

    public static void main(String[] args) {
        SpringApplication.run(MgDollApplication.class, args);
    }

}
