package com.example.customerservice.usrmanagement.dao;


import com.example.core.model.ReturnNo;
import com.example.core.model.ReturnObject;
import com.example.customerservice.usrmanagement.dao.bo.Customer;
import com.example.customerservice.usrmanagement.dao.bo.Staff;
import com.example.customerservice.usrmanagement.mapper.StaffPoMapper;
import com.example.customerservice.usrmanagement.mapper.po.CustomerPo;
import com.example.customerservice.usrmanagement.mapper.po.StaffPo;
import com.example.customerservice.usrmanagement.service.dto.ConnCntDto;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StaffDao {

    private static final Logger logger = LoggerFactory.getLogger(StaffDao.class);
    private StaffPoMapper staffPoMapper;

    @Autowired
    public StaffDao(StaffPoMapper staffPoMapper) {
        this.staffPoMapper = staffPoMapper;
    }

    public void insertStaff(Staff staff){
        StaffPo staffPo = StaffPo.builder().type(staff.getType()).conNum(staff.getConNum()).shopId(staff.getShopId()).username(staff.getUsername()).status(staff.getStatus()).build();
        this.staffPoMapper.save(staffPo);
    }

    public Staff findStaffById(String id){
        Optional<StaffPo> po = staffPoMapper.findById(id);
        if(po.isPresent()){
            StaffPo staffPo = po.get();
            Staff staff = Staff.builder().id(staffPo.getId()).username(staffPo.getUsername())
                    .type(staffPo.getType()).shopId(staffPo.getShopId()).conNum(staffPo.getConNum()).status(staffPo.getStatus()).build();
            return staff;
        }
        return new Staff();
    }

    public ReturnObject putStaffStatus(String id, Byte status){
        Optional<StaffPo> po = staffPoMapper.findById(id);
        logger.debug("po: " + po.get().toString());
        if(po.isPresent()){
            StaffPo staffPo = po.get();
            staffPo.setStatus(status);
            this.staffPoMapper.save(staffPo);
            return new ReturnObject(ReturnNo.OK);
        }else {
            return new ReturnObject(ReturnNo.RESOURCE_ID_NOTEXIST);
        }
    }

    public ConnCntDto getStaffConnCnt(String id){
        Optional<StaffPo> po = staffPoMapper.findById(id);
        if(po.isPresent()){
            StaffPo staffPo = po.get();
            return new ConnCntDto(staffPo.getConNum());
        }else {
            return new ConnCntDto(0);
        }
    }

    public ReturnObject putStaffConnCnt(String id, Integer conncnt){
        Optional<StaffPo> po = staffPoMapper.findById(id);
        if(po.isPresent()){
            StaffPo staffPo = po.get();
            staffPo.setConNum(conncnt);
            this.staffPoMapper.save(staffPo);
            return new ReturnObject(ReturnNo.OK);
        }else {
            return new ReturnObject(ReturnNo.RESOURCE_ID_NOTEXIST);
        }
    }

    public List<Staff> findStaffByIdAndShopIdAndTid(Long id, Long shopId, Long tid){
        List<StaffPo> list = staffPoMapper.findByIdAndShopIdAndType(id, shopId, tid);
        List<Staff> ret = list.stream().map(po->Staff.builder().id(po.getId()).username(po.getUsername()).conNum(po.getConNum()).status(po.getStatus()).type(po.getType()).shopId(po.getShopId()).build()).collect(Collectors.toList());
        return ret;
    }
}
