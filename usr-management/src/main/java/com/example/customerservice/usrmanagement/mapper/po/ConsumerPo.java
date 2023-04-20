package com.example.customerservice.usrmanagement.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "consumer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private Byte priority;
}
