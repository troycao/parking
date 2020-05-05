package com.troy.parking.core.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName TableCardScanPayRequestDto
 * @Description TODO
 * @Author troy
 * @Date 2020/3/30 16:17
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableCardScanPayRequestDto {

    private String instId;
    private String mId;
    private String tId;
    private BigDecimal payAmount;
    private String paymentOrderId;
    /**订单类型默认 innerpay-parkfee*/
    private String orderType;
    private String carNo;
    private String parkingId;
    private String vehicleInTime;
    private String parkStartTime;
}
