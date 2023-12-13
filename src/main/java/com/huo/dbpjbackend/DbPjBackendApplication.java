package com.huo.dbpjbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.huo.dbpjbackend.mapper")
public class DbPjBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbPjBackendApplication.class, args);
    }
//    http://localhost:8080/doc.html#/home
//    http://localhost:8080/swagger-ui/#/
}
