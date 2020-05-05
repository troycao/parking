package com.troy.parking.core.manager.dto;

import lombok.Data;

/**
 * 2019/5/14 10:33
 *
 * @auhor troy
 **/
@Data
public class DoQueryReqDTO {
    private String ORD_ID;
    //private String MerOrderId;
    private String TXNTYPE;

    public DoQueryReqDTO(String ORD_ID, String TXNTYPE) {
        this.ORD_ID = ORD_ID;
        this.TXNTYPE = TXNTYPE;
    }
}
