package com.example.customerservice.usrmanagement.dao;


import com.example.core.model.ReturnNo;
import com.example.core.model.ReturnObject;
import com.example.customerservice.usrmanagement.dao.bo.Customer;
import com.example.customerservice.usrmanagement.mapper.ConsumerPoMapper;
import com.example.customerservice.usrmanagement.mapper.po.CustomerPo;
import com.example.customerservice.usrmanagement.service.dto.StaffDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerDao {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDao.class);

    private ConsumerPoMapper consumerPoMapper;

    @Autowired
    public CustomerDao(ConsumerPoMapper consumerPoMapper) {
        this.consumerPoMapper = consumerPoMapper;
    }

    public void insertConsumer(Customer consumer){
        CustomerPo customerPo = CustomerPo.builder().username(consumer.getUsername()).priority(consumer.getPriority()).build();
        this.consumerPoMapper.save(customerPo);
    }

    public Customer getConsumerById(String id){
        Optional<CustomerPo> po = Optional.ofNullable(consumerPoMapper.findById(id));
        if(po.isPresent()){
            CustomerPo customerPo = po.get();
            Customer customer = Customer.builder().id(customerPo.getId()).username(customerPo.getUsername()).priority(customerPo.getPriority()).build();
            return customer;
        }
        return new Customer();
    }

    public ReturnObject putCustomerPriority(String id, Byte priority){
        CustomerPo customerPo = consumerPoMapper.findById(id);
        if(null != customerPo){
            customerPo.setPriority(priority);
            this.consumerPoMapper.save(customerPo);
            return new ReturnObject(ReturnNo.OK);
        }else {
            return new ReturnObject(ReturnNo.RESOURCE_ID_NOTEXIST);
        }
    }


}
