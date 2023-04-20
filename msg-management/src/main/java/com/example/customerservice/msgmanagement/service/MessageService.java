package com.example.customerservice.msgmanagement.service;

import com.example.customerservice.msgmanagement.dao.MessageDao;
import com.example.customerservice.msgmanagement.dao.bo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    private MessageDao messageDao;

    @Autowired
    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void insertMsgRecord(Byte type, String content, Long senderId, Long receiverId, Long conversationId){
        Message msg = Message.builder().type(type).content(content).senderId(senderId).rcvId(receiverId).conversationId(conversationId).time(LocalDateTime.now())
                .build();
        this.messageDao.insertMsgRecord(msg);
    }
}
