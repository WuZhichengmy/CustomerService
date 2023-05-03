package com.example.customerservice.usrmanagement.controller;

import com.example.core.model.ReturnObject;
import com.example.customerservice.usrmanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/internal", produces = "application/json;charset=UTF-8")
public class InternalController {
    private CustomerService customerService;

    @Autowired
    public InternalController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("customer/{cid}/shop/{shopId}/type/{tid}/assign")
    public ReturnObject assignStaffToCustomer(@PathParam("cid") Long id,
                                               @PathParam("shopId")Long shopId,
                                               @PathParam("tid") Long tid){
        return new ReturnObject(this.customerService.assignStaffToCustomer(id, shopId, tid));
    }

}
