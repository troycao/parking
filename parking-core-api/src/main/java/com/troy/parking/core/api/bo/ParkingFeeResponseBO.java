package com.troy.parking.core.api.bo;

import lombok.Data;

@Data
public class ParkingFeeResponseBO {

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
    //@DateStringFormat(srcFormat = "dd.MM.yyyy HH:mm", descFormat = "yyyy-MM-dd HH:mm:ss")
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

    /***
     * 全局ID:为了确定停车订单唯一编号
     */
    private String gateOrderId;
}
