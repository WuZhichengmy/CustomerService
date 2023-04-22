package com.example.customerservice.usrmanagement.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "service_staff")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffPo {

    @Id
    @GenericGenerator(name = "staffIdGenerator", strategy = "uuid")
    @GeneratedValue(generator = "staffIdGenerator")
    private String id;

    @Column(name = "usrname")
    private String username;

    private Byte type;

    @Column(name = "connect_count")
    private Integer conNum;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "state")
    private Byte status;
}
