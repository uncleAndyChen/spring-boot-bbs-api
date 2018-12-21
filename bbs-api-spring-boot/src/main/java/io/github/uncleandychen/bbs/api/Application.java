package io.github.uncleandychen.bbs.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan({"bbs.api.biz.dal.mapper.original", "bbs.api.biz.dal.mapper.extend"})
// 如果不配置 @ComponentScan，spring boot 启动时，会主动扫描 spring boot 启动类所在 package 以及子 package，所以，这里不用配置扫描路径。
// 如果配置了，则会严格按照配置来扫描，所以，一定要增加配置 spring boot 启动类所在 package，否则 controller 将失效。
@ComponentScan(basePackages = {"bbs.api.common.lib.application", "io.github.uncleandychen.bbs.api"})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

