package com.example.customerservice.usrmanagement.controller;

import com.example.core.model.ReturnNo;
import com.example.core.model.ReturnObject;
import com.example.core.model.dto.PageDto;
import com.example.customerservice.usrmanagement.service.CustomerService;
import com.example.customerservice.usrmanagement.service.dto.ConversationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/consumer", produces = "application/json;charset=UTF-8")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * 根据customerid查询会话
     * @param sid
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/{sid}/conversations")
    public ReturnObject findConversationsByStaffId(@PathParam("sid") String sid,
                                                   @RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer pageSize ){
        PageDto<ConversationDto> conversationDtoPageDto = this.customerService.findConversationsByCustomerId(sid, page, pageSize);
        return new ReturnObject(conversationDtoPageDto);
    }

    /**
     * 根据id查询顾客
     * @param id
     * @return: ReturnObject
     */
    @GetMapping("{id}")
    public ReturnObject findCustomerById(@PathParam("id") Long id){
        return new ReturnObject(this.customerService.findCustomerById(id));
    }


    /**
     * 根据id修改顾客的优先级
     * @param id
     * @param priority
     * @return: ReturnObject
     */
    @PutMapping("{id}/priority")
    public ReturnObject putCustomerPriority(@PathParam("id") Long id,
                                            @RequestParam(defaultValue = "0") Byte priority){
        return this.customerService.putCustomerPriority(id, priority);
    }
}
