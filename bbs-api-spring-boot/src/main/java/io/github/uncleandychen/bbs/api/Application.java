package io.github.uncleandychen.bbs.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan({"bbs.api.biz.dal.mapper.original", "bbs.api.biz.dal.mapper.extend"})
@ComponentScan(basePackages = {"bbs.api.common.lib.application", "io.github.uncleandychen.bbs.api"})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

