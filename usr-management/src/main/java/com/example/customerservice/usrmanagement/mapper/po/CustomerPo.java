package com.example.customerservice.usrmanagement.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerPo {
    @Id
    @GenericGenerator(name = "customerIdGenerator", strategy = "uuid")
    @GeneratedValue(generator = "customerIdGenerator")
    private String id;

    private String username;

    private Byte priority;
}
