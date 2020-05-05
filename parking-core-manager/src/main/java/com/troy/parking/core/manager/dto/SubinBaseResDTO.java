package com.troy.parking.core.manager.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ParkingBaseResponse
 * @Description: 响应基础类
 */
@Data
public class SubinBaseResDTO implements Serializable {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 返回数据
     */
    private String data;

    /**
     * 请求标识
     */
    private String requesttoken;

    /**
     * 签名
     */
    private String sign;

    public boolean isSuccess(){
        return this.success;
    }
}
