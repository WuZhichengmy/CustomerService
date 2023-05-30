package com.example.customerservice.usrmanagement.service;

import com.example.core.model.InternalReturnObject;
import com.example.core.model.ReturnNo;
import com.example.core.model.ReturnObject;
import com.example.core.model.dto.PageDto;
import com.example.customerservice.usrmanagement.dao.CustomerDao;
import com.example.customerservice.usrmanagement.dao.StaffDao;
import com.example.customerservice.usrmanagement.dao.bo.Customer;
import com.example.customerservice.usrmanagement.dao.bo.Staff;
import com.example.customerservice.usrmanagement.dao.openfeign.ConversationDao;
import com.example.customerservice.usrmanagement.service.dto.ConversationDto;
import com.example.customerservice.usrmanagement.service.dto.CustomerDto;
import com.example.customerservice.usrmanagement.service.dto.StaffDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private CustomerDao customerDao;

    private StaffDao staffDao;

    private ConversationDao conversationDao;

    @Autowired
    public CustomerService(CustomerDao customerDao, ConversationDao conversationDao, StaffDao staffDao) {
        this.customerDao = customerDao;
        this.conversationDao = conversationDao;
        this.staffDao = staffDao;
    }

    public PageDto<ConversationDto> findConversationsByCustomerId(String sid, Integer page, Integer pageSize) {
        InternalReturnObject<PageDto<ConversationDto>> conversationsByStaffId = this.conversationDao.findConversationsByCustomerId(sid, page, pageSize);
        return conversationsByStaffId.getData();
    }

    public CustomerDto findCustomerById(String id){
        Customer bo = this.customerDao.getConsumerById(id);
        CustomerDto dto = null;
        if(bo != null){
            dto = CustomerDto.builder().id(bo.getId()).username(bo.getUsername()).priority(bo.getPriority()).build();
        }
//        logger.info("dto: " + dto);
        return dto;
    }

    public ReturnObject putCustomerPriority(String id, Byte priority){
        return this.customerDao.putCustomerPriority(id, priority);
    }

    public StaffDto assignStaffToCustomer(Long id, Long shopId, Long tid){
        //取出所有id、shopId、tid符合的bo对象
        List<Staff> staffList = this.staffDao.findStaffByIdAndShopIdAndTid(id, shopId, tid);
        StaffDto dto = null;
        if(!staffList.isEmpty()){   //非空
            //取出连线数最少的bo
            Staff res = staffList.stream().filter(bo->bo.getStatus().equals(0)).min(Comparator.comparing(Staff::getConNum)).get();
            dto = StaffDto.builder().id(res.getId()).username(res.getUsername())
                    .type(res.getType()).shopId(res.getShopId()).conNum(res.getConNum()).status(res.getStatus()).build();
        }
        return dto;
    }
}
