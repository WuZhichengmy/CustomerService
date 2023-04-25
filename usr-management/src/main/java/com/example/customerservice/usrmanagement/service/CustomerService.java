package com.example.customerservice.usrmanagement.service;

import com.example.core.model.InternalReturnObject;
import com.example.core.model.dto.PageDto;
import com.example.customerservice.usrmanagement.dao.CustomerDao;
import com.example.customerservice.usrmanagement.dao.openfeign.ConversationDao;
import com.example.customerservice.usrmanagement.service.dto.ConversationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerDao customerDao;

    private ConversationDao conversationDao;

    @Autowired
    public CustomerService(CustomerDao customerDao, ConversationDao conversationDao) {
        this.customerDao = customerDao;
        this.conversationDao = conversationDao;
    }

    public PageDto<ConversationDto> findConversationsByCustomerId(String sid, Integer page, Integer pageSize) {
        InternalReturnObject<PageDto<ConversationDto>> conversationsByStaffId = this.conversationDao.findConversationsByCustomerId(sid, page, pageSize);
        return conversationsByStaffId.getData();
    }
}
