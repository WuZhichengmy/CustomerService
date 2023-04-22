package com.example.customerservice.usrmanagement.dao;


import com.example.customerservice.usrmanagement.dao.bo.Staff;
import com.example.customerservice.usrmanagement.mapper.StaffPoMapper;
import com.example.customerservice.usrmanagement.mapper.po.StaffPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StaffDao {

    private StaffPoMapper staffPoMapper;

    @Autowired
    public StaffDao(StaffPoMapper staffPoMapper) {
        this.staffPoMapper = staffPoMapper;
    }

    public void insertStaff(Staff staff){
        StaffPo staffPo = StaffPo.builder().type(staff.getType()).conNum(staff.getConNum()).shopId(staff.getShopId()).username(staff.getUsername()).status(staff.getStatus()).build();
        this.staffPoMapper.save(staffPo);
    }
}
