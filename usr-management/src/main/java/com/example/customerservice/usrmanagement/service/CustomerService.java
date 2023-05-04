package com.example.customerservice.usrmanagement.service;

import com.example.core.model.InternalReturnObject;
import com.example.core.model.ReturnObject;
import com.example.core.model.dto.PageDto;
import com.example.customerservice.usrmanagement.dao.CustomerDao;
import com.example.customerservice.usrmanagement.dao.StaffDao;
import com.example.customerservice.usrmanagement.dao.bo.Customer;
import com.example.customerservice.usrmanagement.dao.bo.Staff;
import com.example.customerservice.usrmanagement.dao.openfeign.ConversationDao;
import com.example.customerservice.usrmanagement.service.dto.ConversationDto;
import com.example.customerservice.usrmanagement.service.dto.CustomerDto;
import com.example.customerservice.usrmanagement.service.dto.StaffDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerDao customerDao;

    private StaffDao staffDao;

    private ConversationDao conversationDao;

    @Autowired
    public CustomerService(CustomerDao customerDao, ConversationDao conversationDao, StaffDao staffDao) {
        this.customerDao = customerDao;
        this.conversationDao = conversationDao;
        this.staffDao = staffDao;
    }

    public PageDto<ConversationDto> findConversationsByCustomerId(String sid, Integer page, Integer pageSize) {
        InternalReturnObject<PageDto<ConversationDto>> conversationsByStaffId = this.conversationDao.findConversationsByCustomerId(sid, page, pageSize);
        return conversationsByStaffId.getData();
    }

    public CustomerDto findCustomerById(Long id){
        Customer bo = this.customerDao.getConsumerById(id);
        CustomerDto dto = CustomerDto.builder().id(bo.getId()).username(bo.getUsername()).priority(bo.getPriority()).build();
        return dto;
    }

    public ReturnObject putCustomerPriority(Long id, Byte priority){
        return this.customerDao.putCustomerPriority(id, priority);
    }

    public StaffDto assignStaffToCustomer(Long id, Long shopId, Long tid){
        Staff bo = this.staffDao.findStaffByIdAndShopIdAndTid(id, shopId, tid).get(0);
        StaffDto dto = StaffDto.builder().id(bo.getId()).username(bo.getUsername())
                .type(bo.getType()).shopId(bo.getShopId()).conNum(bo.getConNum()).status(bo.getStatus()).build();
        return dto;
    }
}
