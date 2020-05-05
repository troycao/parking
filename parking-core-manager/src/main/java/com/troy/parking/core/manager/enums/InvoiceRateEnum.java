package com.troy.parking.core.manager.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: InvoiceRateEnum
 * @Description: 发票费率
 */
public enum InvoiceRateEnum {

    RATE_RZZZF("3040801990000000000", "人证制证费", "0.06", "RZZZF", "001", "21"),
    RATE_CZZZF("3040801990000000000", "车证制证费", "0.06", "CZZZF", "002", "21"),
    RATE_LSTCF("3040801010000000000", "临时停车费", "0.09", "LSTCF", "003", "25"),
    RATE_GDCWF("", "固定车位费", "0.06", "GDCWF", "004", ""),
    RATE_WYGLF("", "物业管理费", "0.06", "WYGLF", "005", ""),
    RATE_FWF("3040801990000000000", "服务费", "0.06", "FWF", "006", "21"),
    RATE_WXF("", "维修费", "0.06", "WXF", "007", ""),
    RATE_QTFY("", "其他费用", "0.06", "QTFY", "009", ""),
    RATE_CDF("3040801010000000000", "充电支付", "0.06", "CDF", "010", "25"),
    ;

    private String code;//一通提供
    private String name;//项目名称
    private String rate;//费率
    private String type;
    private String num;
    private String feeId;//物业费用ID

    InvoiceRateEnum(String code, String name, String rate, String type, String num, String feeId) {
        this.code = code;
        this.name = name;
        this.rate = rate;
        this.type = type;
        this.num = num;
        this.feeId = feeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public static InvoiceRateEnum getInvoiveRateByCode(String code) {
        for (InvoiceRateEnum invoiceRate : InvoiceRateEnum.values()) {
            if (StringUtils.equals(code, invoiceRate.getCode())) {
                return invoiceRate;
            }
        }
        return null;
    }

    public static InvoiceRateEnum getInvoiveRateByType(String type) {
        for (InvoiceRateEnum invoiceRate : InvoiceRateEnum.values()) {
            if (StringUtils.equals(type, invoiceRate.getType())) {
                return invoiceRate;
            }
        }
        return null;
    }

    public static InvoiceRateEnum getInvoiveRateByNum(String num) {
        for (InvoiceRateEnum invoiceRate : InvoiceRateEnum.values()) {
            if (StringUtils.equals(num, invoiceRate.getNum())) {
                return invoiceRate;
            }
        }
        return null;
    }
}
