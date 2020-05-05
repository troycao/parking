package com.troy.parking.core.manager;

import com.troy.parking.core.manager.dto.BarCodePayReqDTO;
import com.troy.parking.core.manager.dto.BarCodePayResDTO;
import com.troy.parking.core.manager.dto.DoQueryReqDTO;
import com.troy.parking.core.manager.dto.DoQueryResDTO;

import java.util.Optional;

public interface QrcWebPayManager {
    public Optional<BarCodePayResDTO> onlineBarCodeDoPay(BarCodePayReqDTO barCodePayReqDTO);


    public Optional<DoQueryResDTO> onlineDoQuery(DoQueryReqDTO doQueryReqDTO);


}
