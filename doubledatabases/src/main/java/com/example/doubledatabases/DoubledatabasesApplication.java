package com.example.doubledatabases;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@MapperScan(basePackages = {"com.example.doubledatabases.dao.primary1","com.example.doubledatabases.dao.primary"})
@SpringBootApplication
@ServletComponentScan(basePackages = {"com.example.doubledatabases.config","com.example.doubledatabases.dao","com.example.doubledatabases.model"
,"com.example.doubledatabases.service","com.example.doubledatabases.service.Impl"
})
public class DoubledatabasesApplication {
//    @PostConstruct
//    void started(){
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
//    }

    public static void main(String[] args) {
        SpringApplication.run(DoubledatabasesApplication.class, args);
    }

}
