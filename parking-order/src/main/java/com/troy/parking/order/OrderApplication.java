package com.troy.parking.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName OrderApplication
 * @Description TODO
 * @Author caoqiang
 * @Date 2020/4/23 15:01
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.troy.parking.order.dao")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
