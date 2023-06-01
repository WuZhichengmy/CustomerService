package com.example.customerservice.instmsg.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ValueConfig {

    @Value("${websocket.service.instance}")
    private String instanceName;

    @Value("${websocket.service.batchmsg}")
    private Boolean batchMsg;

    @Value("${websocket.service.batchmsgcount}")
    private Integer batchMsgCount;
}
