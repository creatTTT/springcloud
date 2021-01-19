package com.tu.springcloud.controller;

import com.tu.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;


    @RequestMapping("/sendMessage")
    public String sendMessage(){
        return messageProvider.send();
    }
}
