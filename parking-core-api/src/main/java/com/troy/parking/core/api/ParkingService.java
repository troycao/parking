package com.troy.parking.core.api;

import com.troy.parking.common.Response;
import com.troy.parking.core.api.bo.ParkingFeeRequestBO;
import com.troy.parking.core.api.bo.ParkingFeeResponseBO;

public interface ParkingService {

    public Response<ParkingFeeResponseBO> checkingParkingFee(ParkingFeeRequestBO parkingFeeRequestBO);
}
