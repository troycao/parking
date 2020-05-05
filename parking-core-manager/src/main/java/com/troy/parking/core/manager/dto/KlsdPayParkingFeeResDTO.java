package com.troy.parking.core.manager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 2019/9/9 15:36
 *
 * @auhor troy
 **/
@NoArgsConstructor
@Data
public class KlsdPayParkingFeeResDTO {
    /**
     * 响应代码（0：正常,1:错误）
     */
    private int resCode;
    /**
     * 响应消息(错误消息)
     */
    private String resMsg;
    /**
     * 总停车时间（分钟）
     */
    private int parkingTime;
}
