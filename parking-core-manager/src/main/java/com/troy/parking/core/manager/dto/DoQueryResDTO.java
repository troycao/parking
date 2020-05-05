package com.troy.parking.core.manager.dto;

import lombok.Data;

/**
 * 2019/5/14 10:33
 *
 * @auhor troy
 **/
@Data
public class DoQueryResDTO {
    private String RSPCOD;
    private String RSPMSG;
    private String ORD_ID;
    private String MerOrderId;
    private String PAY_STATUS;
    private String PAY_DESC;
    private String TRANS_SEQ;
    private String ORI_ORDER_DATE;
    private String ORI_ORDER_TIME;
}
