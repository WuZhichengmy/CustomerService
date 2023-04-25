package com.example.customerservice.msgmanagement.dao;


import com.example.customerservice.msgmanagement.dao.bo.Message;
import com.example.customerservice.msgmanagement.mapper.MessagePoMapper;
import com.example.customerservice.msgmanagement.mapper.po.MessagePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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

    public void insertMsgRecord(Message msg){
        MessagePo messagePo = MessagePo.builder().type(msg.getType()).content(msg.getContent()).senderId(msg.getSenderId()).rcvId(msg.getRcvId())
                .conversationId(msg.getConversationId()).time(msg.getTime()).build();
        this.messagePoMapper.save(messagePo);
    }

    public void deleteMsgRecord(Long msgId) {
        this.messagePoMapper.deleteById(msgId);
    }

    public Message getMsgById(Long msgId) {
        MessagePo messagePo = this.messagePoMapper.findById(msgId).get();
        Message message = Message.builder().id(messagePo.getId()).type(messagePo.getType()).content(messagePo.getContent()).senderId(messagePo.getSenderId()).rcvId(messagePo.getRcvId())
                .conversationId(messagePo.getConversationId()).time(messagePo.getTime()).build();
        return message;
    }

    public List<Message> findByConversationIdPage(Long cid, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<MessagePo> pos = this.messagePoMapper.findByConversationId(cid, pageRequest);
        List<Message> messageList = pos.stream().map(o -> {
            Message message = Message.builder().id(o.getId()).time(o.getTime()).type(o.getType()).conversationId(o.getConversationId())
                    .content(o.getContent()).senderId(o.getSenderId()).rcvId(o.getRcvId()).build();
            return message;
        }).collect(Collectors.toList());
        return messageList;
    }
}
