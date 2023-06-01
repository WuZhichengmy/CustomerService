package com.example.customerservice.usrmanagement.dao;

import com.example.customerservice.usrmanagement.UsrManagementApplication;
import com.example.customerservice.usrmanagement.service.CustomerService;
import com.example.customerservice.usrmanagement.service.dto.CustomerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(classes = UsrManagementApplication.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void findCustomerById(){
        CustomerDto dto = customerService.findCustomerById("0527294a96364b3f2d06bc7ed9539d71");
        Assertions.assertEquals("Shao",dto.getUsername());
        Assertions.assertEquals((byte)1, dto.getPriority());
    }

}
