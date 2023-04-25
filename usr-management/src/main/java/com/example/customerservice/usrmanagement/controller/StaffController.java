package com.example.customerservice.usrmanagement.controller;

import com.example.core.model.ReturnObject;
import com.example.core.model.dto.PageDto;
import com.example.customerservice.usrmanagement.service.StaffService;
import com.example.customerservice.usrmanagement.service.dto.ConversationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/staff", produces = "application/json;charset=UTF-8")
public class StaffController {

    private StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    /**
     * 根据staffid查询会话
     * @param sid
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/{sid}/conversations")
    public ReturnObject findConversationsByStaffId(@PathParam("sid") String sid,
                                                   @RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer pageSize ){
        PageDto<ConversationDto> conversationDtoPageDto = this.staffService.findConversationsByStaffId(sid, page, pageSize);
        return new ReturnObject(conversationDtoPageDto);
    }
}
