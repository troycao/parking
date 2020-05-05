package com.troy.parking.core.manager.enums;

/**
 * 2019/5/15 16:54
 * @auhor troy
 **/
public enum NotifyEnum {
    SUCCESS("SUCCESS","通知成功"),
    FAILED("FAIL","通知失败"),
    TIME_OUT("TIME_OUT","通知超时"),
    IS_NULL("IS_NULL","响应为空")
    ;

    private String code;
    private String msg;

    public boolean equalsCode(String code){
        return this.code.equals(code);
    }
    NotifyEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
