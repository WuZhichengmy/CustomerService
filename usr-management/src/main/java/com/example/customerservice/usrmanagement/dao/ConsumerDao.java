package com.example.customerservice.usrmanagement.dao;


import com.example.customerservice.usrmanagement.mapper.ConsumerPoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConsumerDao {

    private ConsumerPoMapper consumerPoMapper;

    @Autowired
    public ConsumerDao(ConsumerPoMapper consumerPoMapper) {
        this.consumerPoMapper = consumerPoMapper;
    }
}
