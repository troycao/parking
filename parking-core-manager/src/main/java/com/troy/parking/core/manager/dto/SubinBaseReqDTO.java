package com.troy.parking.core.manager.dto;

import com.troy.parking.common.utils.TraceUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ParkingBaseRequest
 * @Description: 请求基础类
 */
@Data
public class SubinBaseReqDTO implements Serializable {

    /**
     * 用户, 默认ljz 使用 gate
     */
    private String user;

    /**
     * 调用接口名
     */
    private String method;

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 请求标识
     */
    private String requesttoken = TraceUtil.getTraceId();

    /**
     * 时间戳
     */
    private String timestamp = String.valueOf(System.currentTimeMillis());

    /**
     * 加密签名
     */
    private String sign;

    /**
     * 业务参数
     */
    private String data;

}
