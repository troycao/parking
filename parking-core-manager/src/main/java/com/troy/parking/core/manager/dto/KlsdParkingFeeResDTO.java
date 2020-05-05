package com.troy.parking.core.manager.dto;

import lombok.Data;

/**
 * 2019/9/9 10:20
 *
 * @auhor zhangyusai
 **/
@Data
public class KlsdParkingFeeResDTO {
    /**
     * 响应代码（0：正常,1:错误）
     */
    private int resCode;
    /**
     * 响应消息(错误消息)
     */
    private String resMsg;
    /**
     * 订单号
     */
    private String order_no;
    /**
     * 总金额，单位分
     */
    private long total_money;
    /**
     * 抵扣金额，单位分
     */
    private long discount_money;
    /**
     * 需支付金额，单位分
     */
    private long need_pay_money;
    /**
     * 使用优惠券数量
     */
    private long coupon_num;
    /**
     * 车场名称
     */
    private String parking_lot_name;
    /**
     * 入场时间
     * (yyyy-MM-dd HH:mm:ss)
     */
    private String in_time;
    /**
     * 停车时长，分钟
     */
    private int parking_time;
    /**
     * 停车订单号
     */
    private String soldld;
}
