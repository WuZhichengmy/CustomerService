package com.example.customerservice.usrmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/consumer", produces = "application/json;charset=UTF-8")
public class ConsumerController {

    private ConsumerController consumerController;

    @Autowired
    public ConsumerController(ConsumerController consumerController) {
        this.consumerController = consumerController;
    }
}
