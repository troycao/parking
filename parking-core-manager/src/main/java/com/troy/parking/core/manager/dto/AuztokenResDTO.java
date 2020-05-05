package com.troy.parking.core.manager.dto;

import lombok.Data;

/**
 * 2019/10/10 16:03
 *
 * @auhor troy
 **/
@Data
public class AuztokenResDTO extends HehoBaseResDTO{

    /**
     * respCode : 0000
     * respMsg : 成功
     * respTxnSsn : 82017112111494614474912811981684
     * respTxnTm : 20171121115034
     * txnTokenId : 82017112111524713507528828110274
     * txnTokenCode : dfasdfadff8ds4f56sa1df65sda65f189awe1f6a5d
     * signAture : jfksadhioanboadFW23uewasdfedcagfjga4W5ad
     */
    private String respTxnSsn;
    private String respTxnTm;
    private String txnTokenId;
    private String txnTokenCode;
}
