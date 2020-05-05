package com.troy.parking.core.manager.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: PaymentEnum
 * @Description: 支付枚举
 */
public enum PaymentEnum {

    SUCCESS("S", "付款成功"),
    FAIL("F", "付款失败"),
    PROCESS("P", "处理中"),
    REFUND("R", "退款"),
    CANCEL("C", "取消支付"),
    NEW("N", "初始化"),
    TIMEOUT("T", "超时"),
    ISNULL("E", "响应为空"),
    WAITING("W", "订单处理中，请等待2-3分钟重试！"),

    channel_wx("05","WECHAT"),
    channel_zfb("06","ALIPAY"),

    ;

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    PaymentEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean equalsCode(String code){
        return this.getCode().equals(code);
    }
    public static PaymentEnum getMsgByCode(String code) {
        for (PaymentEnum respEnum : PaymentEnum.values()) {
            if (StringUtils.equals(code, respEnum.getCode())) {
                return respEnum;
            }
        }
        return null;
    }
}
