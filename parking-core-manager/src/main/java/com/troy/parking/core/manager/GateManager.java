package com.troy.parking.core.manager;

import com.troy.parking.core.manager.dto.GateNotifyDTO;
import com.troy.parking.core.manager.enums.PaymentEnum;

import java.io.IOException;

/**
 * @author troy
 * @date 2020/4/1
 **/
public interface GateManager {

    /**
     * 支付结果通知
     */
    PaymentEnum notify(GateNotifyDTO gateNotifyDTO) throws IOException;


}
