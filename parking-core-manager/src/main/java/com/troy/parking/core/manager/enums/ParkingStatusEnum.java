package com.troy.parking.core.manager.enums;

/**
 * @ClassName: ParkingStatusEnum
 * @Description: 停车状态枚举
 */
public enum ParkingStatusEnum {

    YJC("0","已进场"),
    YCC("1","已出场"),
    ZFZ("2","支付中"),
    CCZ("3","出场中"),
    
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

    ParkingStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean equals(ParkingStatusEnum ps){
        if(ps == null){
            return false;
        }
        return this.code.equals(ps.getCode());
    }

    public boolean equals(String code){
        if(code == null){
            return false;
        }
        return this.code.equalsIgnoreCase(code);
    }
}
