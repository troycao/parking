package com.troy.parking.core.manager.enums;

import org.apache.commons.lang3.StringUtils;

public enum PayTypeEnum {

    SCANCODEFIELD("01","场内扫码"),
    SWEEPEDFEE("02","被扫缴费"),
    NOFEELINGOFFEE("03", "无感代扣"),;

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

    PayTypeEnum(String code, String msg) {
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
