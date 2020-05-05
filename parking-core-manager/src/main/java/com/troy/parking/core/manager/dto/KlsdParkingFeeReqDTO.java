package com.troy.parking.core.manager.dto;

import lombok.Data;

/**
 * 2019/9/9 10:17
 *
 * @auhor troy
 **/
@Data
public class KlsdParkingFeeReqDTO {
    /**
     * 停车场id
     */
    private int parking_lot_id;
    /**
     * 车牌号
     */
    private String lpn;
}
