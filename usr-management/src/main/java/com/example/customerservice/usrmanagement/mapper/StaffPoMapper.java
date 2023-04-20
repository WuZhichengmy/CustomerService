package com.example.customerservicesystem.mapper;

import com.example.customerservicesystem.mapper.po.StaffPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffPoMapper extends JpaRepository<StaffPo, Long> {
}
