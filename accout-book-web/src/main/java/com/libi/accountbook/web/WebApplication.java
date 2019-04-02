package com.libi.accountbook.web;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author libi
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = {"com.libi.accountbook.web.config"})
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
