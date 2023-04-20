package com.example.customerservice.usrmanagement.mapper;

import com.example.customerservice.usrmanagement.mapper.po.StaffPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffPoMapper extends JpaRepository<StaffPo, Long> {
}
