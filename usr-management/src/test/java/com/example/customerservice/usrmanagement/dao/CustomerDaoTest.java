package com.example.customerservice.usrmanagement.dao;

import com.example.customerservice.usrmanagement.UsrManagementApplication;
import com.example.customerservice.usrmanagement.dao.bo.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UsrManagementApplication.class)
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void insertConsumerTest(){
        Customer testuser = Customer.builder().username("testuser").priority((byte) 1).build();
        customerDao.insertConsumer(testuser);
    }

    @Test
    public void getConsumerById(){
        Customer bo = customerDao.getConsumerById("0527294a96364b3f2d06bc7ed9539d71");
        System.out.println(bo);
    }
}
