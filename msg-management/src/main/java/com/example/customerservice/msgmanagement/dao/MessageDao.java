package com.example.customerservice.msgmanagement.dao;


import com.example.customerservice.msgmanagement.dao.bo.InstMessage;
import com.example.customerservice.msgmanagement.mapper.MessagePoMapper;
import com.example.customerservice.msgmanagement.mapper.po.MessagePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public List<InstMessage> findByConversationId(Long conversationId){
        List<MessagePo> byConversationId = this.messagePoMapper.findByConversationId(conversationId);
        List<InstMessage> instMessageList = byConversationId.stream().map(o -> {
            return InstMessage.builder().id(o.getId()).time(o.getTime()).senderId(o.getSenderId()).rcvId(o.getRcvId())
                    .conversationId(o.getConversationId()).type(o.getType()).content(o.getContent()).build();
        }).collect(Collectors.toList());
        return instMessageList;
    }

    public void insertMsgRecord(InstMessage msg){
        MessagePo messagePo = MessagePo.builder().type(msg.getType()).content(msg.getContent()).senderId(msg.getSenderId()).rcvId(msg.getRcvId())
                .conversationId(msg.getConversationId()).time(msg.getTime()).build();
        this.messagePoMapper.save(messagePo);
    }

    public void deleteMsgRecord(Long msgId) {
        this.messagePoMapper.deleteById(msgId);
    }

    public InstMessage getMsgById(Long msgId) {
        MessagePo messagePo = this.messagePoMapper.findById(msgId).get();
        InstMessage instMessage = InstMessage.builder().id(messagePo.getId()).type(messagePo.getType()).content(messagePo.getContent()).senderId(messagePo.getSenderId()).rcvId(messagePo.getRcvId())
                .conversationId(messagePo.getConversationId()).time(messagePo.getTime()).build();
        return instMessage;
    }

    public List<InstMessage> findByConversationIdPage(Long cid, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<MessagePo> pos = this.messagePoMapper.findByConversationId(cid, pageRequest);
        List<InstMessage> instMessageList = pos.stream().map(o -> {
            InstMessage instMessage = InstMessage.builder().id(o.getId()).time(o.getTime()).type(o.getType()).conversationId(o.getConversationId())
                    .content(o.getContent()).senderId(o.getSenderId()).rcvId(o.getRcvId()).build();
            return instMessage;
        }).collect(Collectors.toList());
        return instMessageList;
    }
}
