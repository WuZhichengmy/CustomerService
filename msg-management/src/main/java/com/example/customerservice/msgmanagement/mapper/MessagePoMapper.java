package com.example.customerservice.msgmanagement.mapper;

import com.example.customerservice.msgmanagement.mapper.po.MessagePo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagePoMapper extends JpaRepository<MessagePo, Long> {

    List<MessagePo> findByConversationId(Long id);

    Page<MessagePo> findByConversationId(Long cid, PageRequest pageRequest);
}
