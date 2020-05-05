package com.troy.parking.core.manager.enums;

import com.troy.parking.core.manager.dto.AppletPayResponseDto;
import com.troy.parking.core.manager.dto.BarCodePayResDTO;
import com.troy.parking.core.manager.dto.DoQueryResDTO;
import lombok.Getter;

/**
 * 2019/5/13 15:25
 * @auhor troy
 **/
@Getter
public enum PaymentMethodEnum {
    ONLINE_BARCODE_DOPAY("online_barcode_dopay.xml", BarCodePayResDTO.class,"条码支付"),
    ONLINE_DO_QUERY("online_do_query.xml", DoQueryResDTO.class,"订单查询"),
    ONLINE_APPLET_DOPAY( "initMiniAppPay", AppletPayResponseDto.class, "小程序支付"),
    ;

    PaymentMethodEnum(String serviceName, Class responseType, String desc) {
        this.serviceName = serviceName;
        this.responseType = responseType;
        this.desc = desc;
    }

    String serviceName;
    Class responseType;
    String desc;

}
