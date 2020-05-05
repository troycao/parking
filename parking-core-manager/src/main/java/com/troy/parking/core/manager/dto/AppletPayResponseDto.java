package com.troy.parking.core.manager.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AppletPayResponseDto implements Serializable {
    private String transId;
    private String platformSeq;
    private String channelJSContent;
    private String requestStatus;
    private String respCode;
    private String respMessage;
}
