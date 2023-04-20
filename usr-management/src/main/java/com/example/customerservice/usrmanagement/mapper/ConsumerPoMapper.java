package com.example.customerservice.usrmanagement.mapper;

import com.example.customerservice.usrmanagement.mapper.po.ConsumerPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerPoMapper extends JpaRepository<ConsumerPo, Long> {

}
