package com.troy.parking.core.manager.dto;

import lombok.Data;

/**
 * @Author: troy
 * @Description: 闸机返回
 */
@Data
public class GateChannelRespDTO {

    /**
     * 停车费用
     */
    private String price;

    /**
     * 折扣金额
     */
    private String discount;

    /**
     * 车牌
     */
    private String ticket;

    /**
     * 停车时长
     */
    private String time;

    /**
     * 进场时间  --- yyyy-MM-dd HH:mm:ss
     */
    private String entry;

    /**
     * 缴费次数
     */
    private String paymentnr;

    /**
     * 停车场名称
     */
    private String parkingname;


    /**
     * 响应结果  true 成功，false 失败
     */
    private Boolean result;

    /**
     * 响应信息
     */
    private String message;

    private String gateOrderId;

}
