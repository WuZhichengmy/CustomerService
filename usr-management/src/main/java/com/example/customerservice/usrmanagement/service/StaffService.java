package com.example.customerservice.usrmanagement.service;


import com.example.core.model.InternalReturnObject;
import com.example.core.model.ReturnObject;
import com.example.core.model.dto.PageDto;
import com.example.customerservice.usrmanagement.dao.StaffDao;
import com.example.customerservice.usrmanagement.dao.bo.Customer;
import com.example.customerservice.usrmanagement.dao.bo.Staff;
import com.example.customerservice.usrmanagement.dao.openfeign.ConversationDao;
import com.example.customerservice.usrmanagement.service.dto.ConnCntDto;
import com.example.customerservice.usrmanagement.service.dto.ConversationDto;
import com.example.customerservice.usrmanagement.service.dto.CustomerDto;
import com.example.customerservice.usrmanagement.service.dto.StaffDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    private StaffDao staffDao;

    private ConversationDao conversationDao;

    @Autowired
    public StaffService(StaffDao staffDao, ConversationDao conversationDao) {
        this.staffDao = staffDao;
        this.conversationDao = conversationDao;
    }

    /**
     * 根据staffId查询会话
     * @param sid
     * @param page
     * @param pageSize
     * @return
     */
    public PageDto<ConversationDto> findConversationsByStaffId(String sid, Integer page, Integer pageSize) {
        InternalReturnObject<PageDto<ConversationDto>> conversationsByStaffId = this.conversationDao.findConversationsByStaffId(sid, page, pageSize);
        return conversationsByStaffId.getData();
    }

    public StaffDto findStaffById(String id){
        Staff bo = this.staffDao.findStaffById(id);
        StaffDto dto = StaffDto.builder().id(bo.getId()).username(bo.getUsername())
                .type(bo.getType()).shopId(bo.getShopId()).conNum(bo.getConNum()).status(bo.getStatus()).build();
        return dto;
    }

    public ReturnObject idleServiceStaff(String id){
        return this.staffDao.putStaffStatus(id, (byte) 0);
    }

    public ReturnObject busyServiceStaff(String id){
        return this.staffDao.putStaffStatus(id, (byte) 1);
    }

    public ReturnObject putStaffConnCnt(String id, Integer conncnt){
        return this.staffDao.putStaffConnCnt(id, conncnt);
    }

    public ConnCntDto getStaffConnCnt(String id){
        return this.staffDao.getStaffConnCnt(id);
    }
}
