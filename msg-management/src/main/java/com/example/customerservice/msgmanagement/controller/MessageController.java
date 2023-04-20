package com.example.customerservice.msgmanagement.controller;

import com.example.core.model.ReturnNo;
import com.example.core.model.ReturnObject;
import com.example.customerservice.msgmanagement.controller.vo.MessageVo;
import com.example.customerservice.msgmanagement.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/message", produces = "application/json;charset=UTF-8")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ReturnObject insertMsgRecord(@RequestBody MessageVo messageVo){
        this.messageService.insertMsgRecord(messageVo.getType(), messageVo.getContent(), messageVo.getSenderId(), messageVo.getReceiverId(),
                messageVo.getConversationId());
        return new ReturnObject(ReturnNo.OK);
    }

}
