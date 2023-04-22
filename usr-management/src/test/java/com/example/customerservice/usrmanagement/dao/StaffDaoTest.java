package com.example.customerservice.usrmanagement.dao;

import com.example.customerservice.usrmanagement.UsrManagementApplication;
import com.example.customerservice.usrmanagement.dao.bo.Staff;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UsrManagementApplication.class)
public class StaffDaoTest {
    @Autowired
    private StaffDao staffDao;

    @Test
    public void insertStaffTest(){
        Staff testUsr = Staff.builder().type((byte) 1).conNum(0).shopId(0L).conNum(0).username("testUsr").status((byte) 1).build();
        staffDao.insertStaff(testUsr);
    }
}
