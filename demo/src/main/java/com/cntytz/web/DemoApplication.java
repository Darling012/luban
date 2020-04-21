package com.cntytz.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: luban
 * @description:
 * @author: ling
 * @create: 2020-04-21 17:48
 **/
@SpringBootApplication(scanBasePackages = "com.cntytz")
@MapperScan(basePackages = {"com.cntytz.web.dao.rdbms.repo.mapper"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
