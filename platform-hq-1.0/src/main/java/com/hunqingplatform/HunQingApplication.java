package com.hunqingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAsync//允许异步
@EnableScheduling
@EnableTransactionManagement
@ServletComponentScan
public class HunQingApplication {
	
    public static void main(String[] args) {
    	
        SpringApplication.run(HunQingApplication.class, args);
    }

}
