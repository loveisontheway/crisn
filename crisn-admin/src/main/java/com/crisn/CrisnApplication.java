package com.crisn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author CRISN
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CrisnApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(CrisnApplication.class, args);
    }
}
