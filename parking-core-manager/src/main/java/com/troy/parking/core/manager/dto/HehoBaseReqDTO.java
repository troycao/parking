package com.troy.parking.core.manager.dto;

import cn.hutool.core.date.DatePattern;
import com.troy.parking.common.utils.DateUtil;
import com.troy.parking.common.utils.TraceUtil;
import lombok.Data;

/**
 * 2019/10/10 15:47
 *
 * @auhor troy
 **/
@Data
public class HehoBaseReqDTO {
    /**
     * txnTokenId : 82017112112195373539756116364954
     * reqTxnSsn : 82017112111494614474912811981684
     * reqTxnTm : 20171121115034
     * signAture : jfksadhioan1515sdfFW23uewasdfedcagfjga4W5ad
     */

    private String txnTokenId;
    private String reqTxnSsn;
    private String reqTxnTm;
    private String signAture;

    {
        reqTxnSsn= TraceUtil.getTraceId();
        reqTxnTm = DateUtil.getCurrentDay(DatePattern.PURE_DATETIME_PATTERN);
    }
}
