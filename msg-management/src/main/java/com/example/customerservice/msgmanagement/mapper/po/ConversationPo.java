package com.example.customerservicesystem.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "conversation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationPo {

    @Id
    private Long id;

    private Long consumerId;

    private Long staffId;
}
