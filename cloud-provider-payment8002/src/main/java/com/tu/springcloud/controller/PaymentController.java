package com.tu.springcloud.controller;

import com.tu.springcloud.entities.CommonResult;
import com.tu.springcloud.entities.Payment;
import com.tu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;


    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/creat")
    public CommonResult creat(@RequestBody Payment payment){
        log.info("payment :"+payment.getSerial());
        int result=paymentService.creat(payment);
        log.info("intsert :"+result);
        if(result>0){
            return new CommonResult(200,"success serverpot : "+serverPort,result);
        }else {
            return new CommonResult(400,"false serverpot : "+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment=paymentService.getPaymentById(id);
        log.info("select :"+payment);

        if(payment!=null){
            return new CommonResult(200,"success  serverpot : "+serverPort,payment);
        }else {
            return new CommonResult(400,"false serverpot : "+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLb(){
        return serverPort;
    }
}
