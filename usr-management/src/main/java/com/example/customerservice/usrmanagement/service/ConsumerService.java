package com.example.customerservice.usrmanagement.service;

import com.example.customerservice.usrmanagement.dao.ConsumerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private ConsumerDao consumerDao;

    @Autowired
    public ConsumerService(ConsumerDao consumerDao) {
        this.consumerDao = consumerDao;
    }
}
