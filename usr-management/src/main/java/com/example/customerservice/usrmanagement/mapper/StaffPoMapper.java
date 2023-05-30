package com.example.customerservice.usrmanagement.mapper;

import com.example.customerservice.usrmanagement.dao.bo.Staff;
import com.example.customerservice.usrmanagement.mapper.po.StaffPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffPoMapper extends JpaRepository<StaffPo, Long> {

    Optional<StaffPo> findById(String id);

    List<StaffPo> findByIdAndShopIdAndType(Long id, Long shopId, Long tid);
}
