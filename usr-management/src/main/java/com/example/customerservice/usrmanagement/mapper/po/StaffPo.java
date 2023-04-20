package com.example.customerservice.usrmanagement.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "service_staff")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private Byte type;

    @Column(name = "connect_count")
    private Integer conNum;

    @Column(name = "ship_id")
    private Long shopId;

    @Column(name = "state")
    private Byte status;
}
