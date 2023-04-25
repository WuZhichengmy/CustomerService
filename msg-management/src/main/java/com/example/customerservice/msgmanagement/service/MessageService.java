package com.example.customerservice.msgmanagement.service;

import com.example.core.model.dto.PageDto;
import com.example.customerservice.msgmanagement.dao.MessageDao;
import com.example.customerservice.msgmanagement.dao.bo.Message;
import com.example.customerservice.msgmanagement.service.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private MessageDao messageDao;

    @Autowired
    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    /**
     * 添加Msg记录
     * @param type
     * @param content
     * @param senderId
     * @param receiverId
     * @param conversationId
     */
    public void insertMsgRecord(Byte type, String content, String senderId, String receiverId, Long conversationId){
        Message msg = Message.builder().type(type).content(content).senderId(senderId).rcvId(receiverId).conversationId(conversationId).time(LocalDateTime.now())
                .build();
        this.messageDao.insertMsgRecord(msg);
    }

    /**
     * 删除Msg记录
     * @param msgId
     */
    public void deleteMsgRecord(Long msgId) {
        this.messageDao.deleteMsgRecord(msgId);
    }

    /**
     * 查询某条消息的具体内容
     * @param msgId
     * @return
     */
    public MessageDto getMsgById(Long msgId) {
        Message msg = this.messageDao.getMsgById(msgId);
        MessageDto messageDto = MessageDto.builder().id(msg.getId()).content(msg.getContent()).type(msg.getType()).senderId(msg.getSenderId())
                .rcvId(msg.getRcvId()).time(msg.getTime()).conversationId(msg.getConversationId()).build();
        return messageDto;
    }

    /**
     * 根据会话查询对应msg
     * @param cid
     * @param page
     * @param pageSize
     * @return
     */
    public PageDto<MessageDto> findMsgByConversationId(Long cid, Integer page, Integer pageSize) {
        List<Message> messageList = this.messageDao.findByConversationIdPage(cid, page, pageSize);
        System.out.println(cid);
        System.out.println(messageList);
        List<MessageDto> dtos = messageList.stream().map(o -> {
            MessageDto messageDto = MessageDto.builder().id(o.getId()).type(o.getType()).time(o.getTime()).content(o.getContent())
                    .senderId(o.getSenderId()).rcvId(o.getRcvId()).conversationId(o.getConversationId()).build();
            return messageDto;
        }).collect(Collectors.toList());
        return new PageDto<>(dtos, page, pageSize);
    }
}
