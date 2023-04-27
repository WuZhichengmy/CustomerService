package com.example.customerservice.msgmanagement.service.rocketmq;

import com.example.core.util.JacksonUtil;
import com.example.customerservice.msgmanagement.service.MessageService;
import com.example.customerservice.msgmanagement.service.dto.SimpleMessageDto;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RocketMQMessageListener(consumerGroup = "msg", topic = "insertmsg")
public class MessageListener implements RocketMQListener<String> {
    private MessageService messageService;

    @Autowired
    public MessageListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void onMessage(String str) {
        System.out.println("str:"+str);
        SimpleMessageDto simpleMessageDto = JacksonUtil.toObj(str, SimpleMessageDto.class);
        this.messageService.insertMsgRecord(simpleMessageDto.getType(), simpleMessageDto.getContent(), simpleMessageDto.getSenderId(),
                simpleMessageDto.getRcvId(), simpleMessageDto.getConversationId());
    }
}
