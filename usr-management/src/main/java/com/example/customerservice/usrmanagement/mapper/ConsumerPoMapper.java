package com.example.customerservicesystem.mapper;

import com.example.customerservicesystem.mapper.po.ConsumerPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerPoMapper extends JpaRepository<ConsumerPo, Long> {

}
