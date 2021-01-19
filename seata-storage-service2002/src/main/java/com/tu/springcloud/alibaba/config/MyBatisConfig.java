package com.tu.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.tu.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
