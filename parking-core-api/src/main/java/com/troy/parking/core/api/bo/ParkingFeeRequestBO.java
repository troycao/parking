package com.troy.parking.core.api.bo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ParkingFeeRequestBO implements Serializable {

    /**
     * 用户ID
     */
    String userId;

    /**
     * 车牌
     */
    String carNo;

    /**
     * 是否开通无感
     */
    private boolean isNoninductive;

}
