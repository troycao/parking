package com.troy.parking.core.manager.dto;

import lombok.Data;

@Data
public class BarCodePayResDTO {
    private String RSPCOD;
    private String RSPMSG;
    private String OUTORDERID;
    private String ORDER_ID;
    private String ORD_NO;
    private String ORD_DATE;
    private String ORD_TIME;
    private String PAY_CHANNEL;
    private String BUYERPAYAMOUNT;
    private String POINTAMOUNT;
}
