package com.example.customerservice.msgmanagement.dao;


import com.example.customerservice.msgmanagement.dao.bo.Conversation;
import com.example.customerservice.msgmanagement.mapper.ConversationPoMapper;
import com.example.customerservice.msgmanagement.mapper.po.ConversationPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public void saveConversation(Conversation conversation) {
        ConversationPo conversationPo = ConversationPo.builder().id(conversation.getId()).staffId(conversation.getStaffId()).customerId(conversation.getCustomerId()).status(conversation.getStatus()).build();
        this.conversationPoMapper.save(conversationPo);
    }

    public Conversation getConversationById(Long cid) {
        ConversationPo conversationPo = conversationPoMapper.findById(cid).get();
        Conversation conversation = Conversation.builder().id(conversationPo.getId()).consumerId(conversationPo.getCustomerId())
                .status(conversationPo.getStatus()).staffId(conversationPo.getStaffId()).build();
        setBo(conversation);
        return conversation;
    }

    public void deleteConversation(Long cid) {
        this.conversationPoMapper.deleteById(cid);
    }

    public List<Conversation> findByStaffId(String sid, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<ConversationPo> pos = this.conversationPoMapper.findByStaffId(sid, pageRequest);
        List<Conversation> conversations = pos.stream().map(o -> {
            Conversation conversation = Conversation.builder().id(o.getId()).consumerId(o.getCustomerId()).staffId(o.getStaffId()).status(o.getStatus()).build();
            return conversation;
        }).collect(Collectors.toList());
        return conversations;
    }

    public List<Conversation> findByCustomerId(String sid, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<ConversationPo> pos = this.conversationPoMapper.findByCustomerId(sid, pageRequest);
        List<Conversation> conversations = pos.stream().map(o -> {
            Conversation conversation = Conversation.builder().id(o.getId()).consumerId(o.getCustomerId()).staffId(o.getStaffId()).status(o.getStatus()).build();
            return conversation;
        }).collect(Collectors.toList());
        return conversations;
    }
}
