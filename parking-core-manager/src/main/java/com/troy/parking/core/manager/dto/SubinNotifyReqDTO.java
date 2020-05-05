package com.troy.parking.core.manager.dto;

import lombok.Data;

/**
 * @author troy
 * @date 2020/4/10
 **/
@Data
public class SubinNotifyReqDTO {

    /**
     * 业务编号
     */
    private String id;

    /**
     * 查询方式
     */
    private String way;

    /**
     * 车牌号
     */
    private String ticket;

    /**
     * 支付金额
     */
    private String amount;

    /**
     * 折扣金额
     */
    private String discount;

    /**
     * 支付来源
     */
    private String source;
}
