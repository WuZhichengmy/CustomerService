package com.example.customerservicesystem.mapper;

import com.example.customerservicesystem.mapper.po.MessagePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagePoMapper extends JpaRepository<MessagePo, Long> {

    List<MessagePo> findByConversationId(Long id);
}
