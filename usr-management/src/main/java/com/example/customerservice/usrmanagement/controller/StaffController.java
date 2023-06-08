package com.example.customerservice.usrmanagement.controller;

import com.example.core.model.ReturnNo;
import com.example.core.model.ReturnObject;
import com.example.core.model.dto.PageDto;
import com.example.customerservice.usrmanagement.service.StaffService;
import com.example.customerservice.usrmanagement.service.dto.ConversationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ReturnObject findConversationsByStaffId(@PathVariable("sid") String sid,
                                                   @RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer pageSize ){
        PageDto<ConversationDto> conversationDtoPageDto = this.staffService.findConversationsByStaffId(sid, page, pageSize);
        return new ReturnObject(conversationDtoPageDto);
    }

    /**
     * 根据id查询客服
     * @param id
     * @return: ReturnObject
     */
    @GetMapping("/{id}")
    public ReturnObject findStaffById(@PathVariable("id") String id) throws Exception {
        return new ReturnObject(this.staffService.findStaffById(id));
    }

    /**
     * 将该客服的状态修改为空闲
     * @param id
     * @return: ReturnObject
     */
    @PutMapping("/{id}/idle")
    public ReturnObject idleServiceStaff(@PathVariable("id") String id){
        return this.staffService.idleServiceStaff(id);
    }

    /**
     * 将该客服的状态修改为忙碌
     * @param id
     * @return: ReturnObject
     */
    @PutMapping("/{id}/busy")
    public ReturnObject busyServiceStaff(@PathVariable("id") String id){
        return this.staffService.busyServiceStaff(id);
    }

    /**
     * 获取该客服的连线次数
     * @param id
     * @return: ReturnObject
     */
    @GetMapping("/{id}/conncnt")
    public ReturnObject getStaffConnCnt(@PathVariable("id") String id){
        return new ReturnObject(this.staffService.getStaffConnCnt(id));
    }

    /**
     * 修改该客服的连线次数
     * @param id
     * @param conncnt
     * @return: ReturnObject
     * @Author: cjr
     */
    @PutMapping("/{id}/conncnt")
    public ReturnObject putStaffConnCnt(@PathVariable("id") String id,
                                        @RequestParam(defaultValue = "0") Integer conncnt){
        return this.staffService.putStaffConnCnt(id, conncnt);
    }

}
