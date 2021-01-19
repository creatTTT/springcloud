package com.tu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class orderZKMain80 {
    public static void main(String[] args) {
        SpringApplication.run(orderZKMain80.class,args);
    }
}
