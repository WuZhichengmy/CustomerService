package com.example.customerservicesystem.dao;

import com.example.customerservicesystem.dao.bo.Message;
import com.example.customerservicesystem.mapper.MessagePoMapper;
import com.example.customerservicesystem.mapper.po.MessagePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MessageDao {

    private MessagePoMapper messagePoMapper;

    @Autowired
    public MessageDao(MessagePoMapper messagePoMapper) {
        this.messagePoMapper = messagePoMapper;
    }

    public List<Message> findByConversationId(Long conversationId){
        List<MessagePo> byConversationId = this.messagePoMapper.findByConversationId(conversationId);
        List<Message> messageList = byConversationId.stream().map(o -> {
            return Message.builder().id(o.getId()).time(o.getTime()).senderId(o.getSenderId()).rcvId(o.getRcvId())
                    .conversationId(o.getConversationId()).type(o.getType()).content(o.getContent()).build();
        }).collect(Collectors.toList());
        return messageList;
    }
}
