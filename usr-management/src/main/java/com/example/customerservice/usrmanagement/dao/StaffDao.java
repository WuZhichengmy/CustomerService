package com.example.customerservicesystem.dao;

import com.example.customerservicesystem.mapper.StaffPoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StaffDao {

    private StaffPoMapper staffPoMapper;

    @Autowired
    public StaffDao(StaffPoMapper staffPoMapper) {
        this.staffPoMapper = staffPoMapper;
    }
}
