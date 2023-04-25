package com.example.customerservice.usrmanagement.dao;

import com.example.core.model.InternalReturnObject;
import com.example.core.model.dto.PageDto;
import com.example.customerservice.usrmanagement.UsrManagementApplication;
import com.example.customerservice.usrmanagement.dao.bo.Customer;
import com.example.customerservice.usrmanagement.dao.openfeign.ConversationDao;
import com.example.customerservice.usrmanagement.service.dto.ConversationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UsrManagementApplication.class)
public class ConversationDaoTest {
    @Autowired
    private ConversationDao conversationDao;

    @Test
    public void testFindConversationByCustomerId(){
        InternalReturnObject<PageDto<ConversationDto>> conversations = conversationDao.findConversationsByCustomerId("bbdd22b4eae8b21b2ba973ee27b15c00", 0, 10);
        System.out.println(conversations.getData().getList());
    }

    @Test
    public void testFindConversationByStaffId(){
        InternalReturnObject<PageDto<ConversationDto>> conversations = conversationDao.findConversationsByStaffId("09c4e678158215a0f215619e1458755e", 0, 10);
        System.out.println(conversations.getData().getList());
    }
}
