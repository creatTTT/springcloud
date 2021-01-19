package com.tu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return  "线程池：" +Thread.currentThread().getName()+" paymentInfo_OK  , id ="+id+"     Success!!! ";
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value ="3000" )})
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber=5;
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //int age=10/0;
        return  "线程池：" +Thread.currentThread().getName()+" paymentInfo_TimeOut  , id ="+id+"     Success!!!   耗时:";
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return  "8001端  线程池：" +Thread.currentThread().getName()+" 系统繁忙或运行报错，请稍后重试。  , id = "+id+" oooooooooooo";
    }
}
