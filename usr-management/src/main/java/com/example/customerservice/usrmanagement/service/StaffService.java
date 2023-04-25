package com.example.customerservice.usrmanagement.service;


import com.example.core.model.InternalReturnObject;
import com.example.core.model.dto.PageDto;
import com.example.customerservice.usrmanagement.dao.StaffDao;
import com.example.customerservice.usrmanagement.dao.openfeign.ConversationDao;
import com.example.customerservice.usrmanagement.service.dto.ConversationDto;
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
}
