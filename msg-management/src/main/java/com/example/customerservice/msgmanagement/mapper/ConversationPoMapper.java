package com.example.customerservicesystem.mapper;

import com.example.customerservicesystem.mapper.po.ConversationPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationPoMapper extends JpaRepository<ConversationPo, Long> {

    List<ConversationPo> findByConsumerId(Long id);

    List<ConversationPo> findByStaffId(Long id);
}
