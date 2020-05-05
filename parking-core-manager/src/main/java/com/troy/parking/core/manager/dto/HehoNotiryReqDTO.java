package com.troy.parking.core.manager.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: troy
 * @Description: 通知
 * @Date: Created in 19-10-15 - 下午2:42
 * @Version: V1.0
 */
@Data
public class HehoNotiryReqDTO extends HehoBaseReqDTO{
    private String carPayId;
    private String payState;
    private String payTxnTm;
    private List<Pay> payList;

    @Data
    public class Pay {
        private String  payTm;
        private String  payType;
        private String  payTxnNo;
        private String  payAmt;
    }
}



