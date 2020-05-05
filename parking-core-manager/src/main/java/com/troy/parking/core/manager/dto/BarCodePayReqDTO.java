package com.troy.parking.core.manager.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BarCodePayReqDTO {
    private String INSTID;
    private String USRID;
    private String OUTORDERID;
    private BigDecimal TXAMT;
    private String BODY;
    private String TXNTYPE;
    private String NOTIFYURL;
    private String SCENE;
    private String AUTHCODE;
    private String SUBJECT;
    private String PLATE;
    private String PARKSTARTTIME;
    private String LOTID;
    private String PAYSOURCE = "parkfee";

}
