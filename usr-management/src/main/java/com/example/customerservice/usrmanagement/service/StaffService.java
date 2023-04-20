package com.example.customerservicesystem.service;

import com.example.customerservicesystem.dao.MessageDao;
import com.example.customerservicesystem.dao.StaffDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    private StaffDao staffDao;

    private MessageDao messageDao;

    @Autowired
    public StaffService(StaffDao staffDao, MessageDao messageDao) {
        this.staffDao = staffDao;
        this.messageDao = messageDao;
    }
}
