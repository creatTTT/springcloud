package com.tu.springcloud.controller;

import com.tu.springcloud.entities.CommonResult;
import com.tu.springcloud.entities.Payment;
import com.tu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/creat")
    public CommonResult creat(@RequestBody Payment payment){
        log.info("payment :"+payment.getSerial());
        int result=paymentService.creat(payment);
        log.info("intsert :"+result);
        if(result>0){
            return new CommonResult(200,"success  serverport :"+serverPort,result);
        }else {
            return new CommonResult(400,"false  serverport :"+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment=paymentService.getPaymentById(id);
        log.info("select :"+payment);

        if(payment!=null){
            return new CommonResult(200,"success serverport :"+serverPort,payment);
        }else {
            return new CommonResult(400,"false  serverport :"+serverPort,null);
        }
    }


    @GetMapping(value = "/payment/lb")
    public String getPaymentLb(){
        return serverPort;
    }


    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();

        for(String element:services){
            log.info("-------element:"+element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance: instances){
            log.info(instance.getServiceId()+"    "+instance.getHost()+"    "+instance.getPort()+"    "+instance.getUri());
        }

        return this.discoveryClient;
    }


    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
