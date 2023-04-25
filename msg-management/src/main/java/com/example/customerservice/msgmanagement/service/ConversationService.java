package com.example.customerservice.msgmanagement.service;

import com.example.core.model.dto.PageDto;
import com.example.customerservice.msgmanagement.dao.ConversationDao;
import com.example.customerservice.msgmanagement.dao.bo.Conversation;
import com.example.customerservice.msgmanagement.service.dto.ConversationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConversationService {
    private ConversationDao conversationDao;

    @Autowired
    public ConversationService(ConversationDao conversationDao) {
        this.conversationDao = conversationDao;
    }

    /**
     * 新增一个会话，并将状态设为进行中
     * @param staffId
     * @param customerId
     */
    public void insertConversation(String staffId, String customerId) {
        Conversation conversation = Conversation.builder().consumerId(customerId).staffId(staffId).status((byte) 1).build();
        this.conversationDao.saveConversation(conversation);
    }

    /**
     * 关闭会话
     * @param cid
     */
    public void closeConversation(Long cid) {
        Conversation conversation = this.conversationDao.getConversationById(cid);
        conversation.setStatus((byte) 0);
        this.conversationDao.saveConversation(conversation);
    }

    /**
     * 查询会话的具体内容
     * @param cid
     * @return
     */
    public ConversationDto getConversationById(Long cid) {
        Conversation conversation = this.conversationDao.getConversationById(cid);
        ConversationDto conversationDto = ConversationDto.builder().id(conversation.getId()).staffId(conversation.getStaffId()).customerId(conversation.getCustomerId())
                .status(conversation.getStatus()).build();
        return conversationDto;
    }

    /**
     * 删除会话
     * @param cid
     */
    public void deleteConversation(Long cid) {
        this.conversationDao.deleteConversation(cid);
    }

    /**
     * 根据staff的id查找
     * @param sid
     * @param page
     * @param pageSize
     * @return
     */
    public PageDto<ConversationDto> findByStaffId(String sid, Integer page, Integer pageSize) {
        List<Conversation> conversationList = this.conversationDao.findByStaffId(sid, page, pageSize);
        List<ConversationDto> collect = conversationList.stream().map(o -> {
            ConversationDto conversationDto = ConversationDto.builder().id(o.getId()).status(o.getStatus()).staffId(o.getStaffId()).customerId(o.getCustomerId()).build();
            return conversationDto;
        }).collect(Collectors.toList());
        return new PageDto<>(collect, page, pageSize);
    }

    /**
     * 根据consumer的id查找
     * @param sid
     * @param page
     * @param pageSize
     * @return
     */
    public PageDto<ConversationDto> findByCustomerId(String sid, Integer page, Integer pageSize) {
        List<Conversation> conversationList = this.conversationDao.findByCustomerId(sid, page, pageSize);
        List<ConversationDto> collect = conversationList.stream().map(o -> {
            ConversationDto conversationDto = ConversationDto.builder().id(o.getId()).status(o.getStatus()).staffId(o.getStaffId()).customerId(o.getCustomerId()).build();
            return conversationDto;
        }).collect(Collectors.toList());
        return new PageDto<>(collect, page, pageSize);
    }
}
