package com.troy.parking.core.manager.dto;

import com.troy.parking.core.entity.PaymentOrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author troy
 * @date 2020/4/4
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GateNotifyDTO {
    private PaymentOrderEntity paymentOrder;
    private String carNo;
}
