package com.magee.webo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.magee.springboot.mapper")
@SpringBootApplication
public class SpringDemoWeboApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoWeboApplication.class, args);
    }

}
