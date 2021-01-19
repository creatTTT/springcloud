package com.tu.springcloud.service.impl;

import com.tu.springcloud.dao.PaymentDao;
import com.tu.springcloud.entities.Payment;
import com.tu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int creat(Payment payment){
        return paymentDao.creat(payment);
    }

    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }

}
