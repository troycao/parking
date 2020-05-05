package com.troy.parking.core.manager.dto;

import lombok.Data;

/**
 * 2019/9/9 15:35
 *
 * @auhor troy
 **/
@Data
public class KlsdPayParkingFeeReqDTO {
    /**
     * 唯一查询标识号
     */
    private String order_no;
    /**
     * 支付金额，单位分
     */
    private long pay_money;
    /**
     * 支付时间（作为出场时间）
     * (yyyy-MM-dd HH:mm:ss)
     */
    private String pay_time;
    /**
     * 车牌号
     */
    private String lpn;
}
