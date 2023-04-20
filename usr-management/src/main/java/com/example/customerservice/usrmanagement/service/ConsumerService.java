package com.example.customerservicesystem.service;

import com.example.customerservicesystem.dao.ConsumerDao;
import com.example.customerservicesystem.dao.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private ConsumerDao consumerDao;

    private MessageDao messageDao;

    @Autowired
    public ConsumerService(ConsumerDao consumerDao, MessageDao messageDao) {
        this.consumerDao = consumerDao;
        this.messageDao = messageDao;
    }
}
