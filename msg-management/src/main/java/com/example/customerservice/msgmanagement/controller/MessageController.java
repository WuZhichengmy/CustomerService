package com.example.customerservice.msgmanagement.controller;

import com.example.core.model.ReturnNo;
import com.example.core.model.ReturnObject;
import com.example.customerservice.msgmanagement.controller.vo.MessageVo;
import com.example.customerservice.msgmanagement.service.MessageService;
import com.example.customerservice.msgmanagement.service.dto.MessageDto;
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

    /**
     * 添加一条消息记录
     * @param messageVo
     * @return
     */
    @PostMapping
    public ReturnObject insertMsgRecord(@RequestBody MessageVo messageVo){
        this.messageService.insertMsgRecord(messageVo.getType(), messageVo.getContent(), messageVo.getSenderId(), messageVo.getReceiverId(),
                messageVo.getConversationId());
        return new ReturnObject(ReturnNo.OK);
    }

    /**
     * 删除一条消息记录
     * @param msgId
     * @return
     */
    @DeleteMapping("/{mid}")
    public ReturnObject deleteMsgRecord(@PathVariable("mid") Long msgId){
        this.messageService.deleteMsgRecord(msgId);
        return new ReturnObject(ReturnNo.OK);
    }

    /**
     * 查询某条消息的具体内容
     * @param msgId
     * @return
     */
    @GetMapping("/{mid}")
    public ReturnObject getMsgById(@PathVariable("mid") Long msgId){
        MessageDto messageDto = this.messageService.getMsgById(msgId);
        return new ReturnObject(messageDto);
    }
}
