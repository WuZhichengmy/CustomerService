package com.example.customerservice.msgmanagement.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MessagePo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Byte type;

    private String content;

    @Column(name = "sender_id")
    private String senderId;

    @Column(name = "receiver_id")
    private String rcvId;

    private LocalDateTime time;

    @Column(name = "conversation_id")
    private Long conversationId;
}
