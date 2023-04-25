package com.example.customerservice.msgmanagement.controller;

import com.example.core.model.ReturnObject;
import com.example.core.model.dto.PageDto;
import com.example.customerservice.msgmanagement.service.ConversationService;
import com.example.customerservice.msgmanagement.service.dto.ConversationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/internal", produces = "application/json;charset=UTF-8")
public class InternalController {
    private ConversationService conversationService;

    @Autowired
    public InternalController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping("/staff/{sid}/conversation")
    public ReturnObject findByStaffId(@PathVariable("sid") String sid,
                                      @RequestParam Integer page,
                                      @RequestParam Integer pageSize){
        PageDto<ConversationDto> conversationDtoPageDto = this.conversationService.findByStaffId(sid, page, pageSize);
        return new ReturnObject(conversationDtoPageDto);
    }

    @GetMapping("/customer/{sid}/conversation")
    public ReturnObject findByCustomerId(@PathVariable("sid") String sid,
                                      @RequestParam Integer page,
                                      @RequestParam Integer pageSize){
        PageDto<ConversationDto> conversationDtoPageDto = this.conversationService.findByCustomerId(sid, page, pageSize);
        return new ReturnObject(conversationDtoPageDto);
    }
}
