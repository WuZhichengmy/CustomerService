package com.example.customerservice.msgmanagement.dao;


import com.example.customerservice.msgmanagement.dao.bo.Conversation;
import com.example.customerservice.msgmanagement.mapper.ConversationPoMapper;
import com.example.customerservice.msgmanagement.mapper.po.ConversationPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ConversationDao {

    private ConversationPoMapper conversationPoMapper;

    private MessageDao messageDao;

    @Autowired
    public ConversationDao(ConversationPoMapper conversationPoMapper, MessageDao messageDao) {
        this.conversationPoMapper = conversationPoMapper;
        this.messageDao = messageDao;
    }

    private void setBo(Conversation conversation){
        conversation.setMessageDao(messageDao);
    }

}
