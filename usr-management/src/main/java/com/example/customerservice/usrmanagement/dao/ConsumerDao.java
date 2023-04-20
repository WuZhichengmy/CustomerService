package com.example.customerservicesystem.dao;

import com.example.customerservicesystem.mapper.ConsumerPoMapper;
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
