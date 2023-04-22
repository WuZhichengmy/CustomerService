package com.example.customerservice.usrmanagement.dao;


import com.example.customerservice.usrmanagement.dao.bo.Customer;
import com.example.customerservice.usrmanagement.mapper.ConsumerPoMapper;
import com.example.customerservice.usrmanagement.mapper.po.CustomerPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

    private ConsumerPoMapper consumerPoMapper;

    @Autowired
    public CustomerDao(ConsumerPoMapper consumerPoMapper) {
        this.consumerPoMapper = consumerPoMapper;
    }

    public void insertConsumer(Customer consumer){
        CustomerPo customerPo = CustomerPo.builder().username(consumer.getUsername()).priority(consumer.getPriority()).build();
        this.consumerPoMapper.save(customerPo);
    }
}
