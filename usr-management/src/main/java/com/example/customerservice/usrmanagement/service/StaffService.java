package com.example.customerservice.usrmanagement.service;


import com.example.customerservice.usrmanagement.dao.StaffDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    private StaffDao staffDao;

    @Autowired
    public StaffService(StaffDao staffDao) {
        this.staffDao = staffDao;
    }
}
