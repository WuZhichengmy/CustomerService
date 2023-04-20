package com.example.customerservice.msgmanagement.mapper;

import com.example.customerservice.msgmanagement.mapper.po.ConversationPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationPoMapper extends JpaRepository<ConversationPo, Long> {
}
