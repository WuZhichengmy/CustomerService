package com.example.customerservicesystem.dao;

import com.example.customerservicesystem.dao.bo.Conversation;
import com.example.customerservicesystem.mapper.ConversationPoMapper;
import com.example.customerservicesystem.mapper.po.ConversationPo;
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

    public List<Conversation> findByConsumerId(Long consumerId){
        List<ConversationPo> byConsumerId = this.conversationPoMapper.findByConsumerId(consumerId);
        List<Conversation> collect = byConsumerId.stream().map(o -> {
            Conversation build = Conversation.builder().id(o.getId()).consumerId(o.getConsumerId()).staffId(o.getStaffId()).build();
            setBo(build);
            return build;
        }).collect(Collectors.toList());
        return collect;
    }

    public List<Conversation> findByStaffId(Long staffId){
        List<ConversationPo> byConsumerId = this.conversationPoMapper.findByStaffId(staffId);
        List<Conversation> collect = byConsumerId.stream().map(o -> {
            Conversation build = Conversation.builder().id(o.getId()).consumerId(o.getConsumerId()).staffId(o.getStaffId()).build();
            setBo(build);
            return build;
        }).collect(Collectors.toList());
        return collect;
    }
}
