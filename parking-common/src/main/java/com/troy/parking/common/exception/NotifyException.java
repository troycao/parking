package com.troy.parking.common.exception;

import lombok.Data;

/**
 * @ClassName: NotifyException
 * @Description: 支付结果通知异常
 * @Author: wangchangyong
 * @Date: 2018/12/27 16:23
 * @Version: 1.0.0
 */
@Data
public class NotifyException extends Exception {
    public NotifyException(String message) {
        super(message);
    }
}
