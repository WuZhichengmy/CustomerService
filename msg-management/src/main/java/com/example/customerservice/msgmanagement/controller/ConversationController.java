package com.example.customerservice.msgmanagement.controller;

import com.example.core.model.ReturnNo;
import com.example.core.model.ReturnObject;
import com.example.core.model.dto.PageDto;
import com.example.customerservice.msgmanagement.controller.vo.ConversationVo;
import com.example.customerservice.msgmanagement.service.ConversationService;
import com.example.customerservice.msgmanagement.service.MessageService;
import com.example.customerservice.msgmanagement.service.dto.ConversationDto;
import com.example.customerservice.msgmanagement.service.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/conversation", produces = "application/json;charset=UTF-8")
public class ConversationController {

    private ConversationService conversationService;

    private MessageService messageService;

    @Autowired
    public ConversationController(ConversationService conversationService, MessageService messageService) {
        this.conversationService = conversationService;
        this.messageService = messageService;
    }

    /**
     * 新增一个会话
     * @param conversationVo
     * @return
     */
    @PostMapping
    public ReturnObject insertConversation(@RequestBody ConversationVo conversationVo){
        this.conversationService.insertConversation(conversationVo.getStaffId(), conversationVo.getCustomerId());
        return new ReturnObject(ReturnNo.OK);
    }

    /**
     * 关闭会话
     * @param cid
     * @return
     */
    @PutMapping("/{cid}/close")
    public ReturnObject closeConversation(@PathVariable("cid") Long cid){
        this.conversationService.closeConversation(cid);
        return new ReturnObject(ReturnNo.OK);
    }

    /**
     * 查询一个会话的内容
     * @param cid
     * @return
     */
    @GetMapping("/{cid}")
    public ReturnObject getConversationById(@PathVariable("cid") Long cid){
        ConversationDto conversationDto = this.conversationService.getConversationById(cid);
        return new ReturnObject(conversationDto);
    }

    /**
     * 删除会话
     * @return
     */
    @DeleteMapping("/{cid}")
    public ReturnObject deleteConversation(@PathVariable("cid") Long cid){
        this.conversationService.deleteConversation(cid);
        return new ReturnObject(ReturnNo.OK);
    }

    /**
     * 查找会话中的所有消息
     * @param cid
     * @return
     */
    @GetMapping("/{cid}/messages")
    public ReturnObject findMsgsByConversation(@PathVariable("cid") Long cid,
                                               @RequestParam(defaultValue = "0") Integer page,
                                               @RequestParam(defaultValue = "10") Integer pageSize){
        PageDto<MessageDto> messageDtoPageDto = this.messageService.findMsgByConversationId(cid, page, pageSize);
        return new ReturnObject(messageDtoPageDto);
    }
}
