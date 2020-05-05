package com.troy.parking.core.manager.dto;

import lombok.Data;

/**
 * 2019/10/10 16:03
 *
 * @auhor troy
 **/
@Data
public class AuztokenReqDTO extends HehoBaseReqDTO{

    /**
     * auzId : 82017112111494614474904791920684
     * reqTxnSsn : 82017112111494614474912811981684
     * reqTxnTm : 20171121115034
     * auzType : car
     * version : 1.0
     * signAture : jfksadhioanboadFW23uewasdfedcagfjga4W5ad
     */
    private String auzId;
    private String auzType="car";
    private String version="1.0";

}