package com.troy.parking.core.manager.dto;


import com.troy.parking.core.entity.ParkingOrderEntity;
import com.troy.parking.core.manager.enums.PayTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 2020/3/12 19:26
 *
 * @auhor troy
 **/
@Data
@AllArgsConstructor
public class InitPaymentParamDTO {
    /**
     * 支付类型
     */
    public PayTypeEnum payType;
    /**
     * 支付订单号
     */
    public String paymentOrderId;
    /**
     * 支付金额
     */
    public BigDecimal payAmount;
    /**
     * 支付渠道  “05”-微信，“06”-支付宝
     */
    public String payChannel;
    /**
     * 停车订单
     */
    public ParkingOrderEntity parkingOrder;
    /**
     * 异步通知地址
     */
    public String notifyUrl;

    public InitPaymentParamDTO(PayTypeEnum payType,String paymentOrderId, BigDecimal payAmount, String payChannel, ParkingOrderEntity parkingOrder) {
        this.paymentOrderId = paymentOrderId;
        this.payAmount = payAmount;
        this.payChannel = payChannel;
        this.parkingOrder = parkingOrder;
        this.payType = payType;
    }
}
