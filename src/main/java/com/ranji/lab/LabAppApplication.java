package com.ranji.lab;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = {"com.ranji.lab.mapper"})
@EnableCaching
public class LabAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabAppApplication.class, args);
    }

}
