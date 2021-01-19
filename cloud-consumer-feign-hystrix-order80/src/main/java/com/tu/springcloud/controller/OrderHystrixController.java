package com.tu.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @RequestMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") int id){
        String result=paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    /*@HystrixCommand(fallbackMethod = "paymentTimeoutFallbackHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value ="1500" )})*/
    @HystrixCommand
    @RequestMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeOut(@PathVariable("id") int id){
        String result=paymentHystrixService.paymentInfo_timeOut(id);
        return result;
    }

    public String paymentTimeoutFallbackHandler(int id){
        return  "80端  线程池：" +Thread.currentThread().getName()+" 系统繁忙或运行报错，请稍后重试。  , id = "+id+" oooooooooooo";
    }


    // 下面是全局fallback方法
    public String payment_Global_FallbackMethod()
    {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
