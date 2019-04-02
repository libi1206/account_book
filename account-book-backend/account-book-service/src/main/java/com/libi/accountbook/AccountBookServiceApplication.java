package com.libi.accountbook;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author libi
 */
@SpringBootApplication
@MapperScan("com.libi.accountbook.dao")
@EnableDubbo(scanBasePackages = {"com.libi.accountbook.service.impl"})
public class AccountBookServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountBookServiceApplication.class, args);
    }
}
