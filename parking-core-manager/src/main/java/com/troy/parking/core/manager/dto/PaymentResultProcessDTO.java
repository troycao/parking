package com.troy.parking.core.manager.dto;

import com.troy.parking.core.entity.PaymentOrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author troy
 * @date 2020/3/23
 **/
@Data
@AllArgsConstructor
public class PaymentResultProcessDTO {
    /**
     * 停车支付流水
     */
    private PaymentOrderEntity paymentOrderEntity;
}
