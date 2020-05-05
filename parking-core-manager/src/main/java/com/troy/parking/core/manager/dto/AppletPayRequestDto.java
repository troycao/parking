package com.troy.parking.core.manager.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AppletPayRequestDto implements Serializable {

    /**订单ID*/
    private String orderId;
    /**IP地址*/
    private String ipAddr;
    /**商户号*/
    private String mId;
    /**终端号*/
    private String tId;
    /**交易金额*/
    private BigDecimal transAmt;
    /**商户订单*/
    private String merOrderId;
    /**微信openid*/
    private String subOpenId;
    /**微信appid*/
    private String subAppId;
    private String instId;
    private String orderDate;
    private String orderTime;
    /**回调URL*/
    private String notifyUrl;
    /**支付宝userid*/
    private String subUserId;
    /**用户支付方式*/
    private String miniAppType;
    private String needReceipt;

}
