package com.troy.parking.core.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableRabbit
@MapperScan("com.troy.parking.core.dao")
@ComponentScan("com.troy")
@SpringBootApplication
public class ParkingCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingCoreApplication.class, args);
    }
}
