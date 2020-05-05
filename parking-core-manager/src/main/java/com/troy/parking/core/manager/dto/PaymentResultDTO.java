package com.troy.parking.core.manager.dto;

import lombok.Data;

/**
 * @author troy
 * @date 2020/3/23
 **/
@Data
public class PaymentResultDTO {
    /**
     * 停车支付流水号
     */
    private String paymentOrderId;
    /**
     * 支付中心流水号
     */
    private String payChannleOrder;
    /**
     * 支付状态
     */
    private String status;
    /**
     * 第三方支付流水号 微信/支付宝
     */
    private String payThirdChannleOrder;
    /**
     * 微信 支付宝 用户id
     */
    private String userId;

    public PaymentResultDTO(String paymentOrderId, String payChannleOrder, String status) {
        this.paymentOrderId = paymentOrderId;
        this.payChannleOrder = payChannleOrder;
        this.status = status;
    }
}
