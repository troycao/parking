package com.troy.parking.common.enums;

/**
 * @ClassName: ExceptionEnum
 * @Description: 异常枚举
 * @Author: troy
 * @Date: 2018/9/21 0021 15:47
 * @Version: 1.0.0
 */
public enum ExceptionEnum {

    SUCCESS("0000","交易成功"),
    PROCESS("3001","处理中"),
    FAIL("9999","交易失败"),


    PAYMENT_ORDER_ERROR("0001","订单支付异常"),
    PAYMENT_ORDER_EMPTY("0002","支付订单不存在"),
    QUERY_PAYMENT_ERROR("0003","查询支付订单异常"),
    QUERY_PARKING_FEE_ERROR("0004","查询停车费用信息异常"),
    PARKING_FEE_EMPTY("0005","停车信息不存在"),
    PAY_PARKING_FEE_ERROR("0006","停车费用支付异常"),
    CANCEL_PAYMENT_ERROR("0007","取消支付订单异常"),
    INVOICE_CREATE_ERROR("0008","开票异常,请稍后重试"),
    INVOICE_INFO_EMPTY("0009","发票信息不存在"),
    CLOSE_PAYMENT_ERROR("0010","关闭支付订单异常"),
    INVOICE_CREATE_TIMEOUT("0011","开票信息已过有效期"),
    QUERY_PARKING_FEE_TOMEOUT("0012","查询停车费用信息超时"),
    XZ_ERROR("0013","销账异常,无法开票"),
    NOT_FOUND_MER("0014","商户信息不存在"),
    WAITING("0015","订单处理中，请等待2-3分钟重试！"),
    ERROR_PAYMENT_STATUS("0016","订单状态异常！"),
	SUCCESSED("0017","该订单已经支付成功！"),
	PAY_PARKING_FEE_PAYING("0018","车辆费用支付中"),
    ALREADY_LEAVE("0019","车辆已出场"),
    INVOICE_OF_REPETITION("I019","开票申请重复"),
    PAYMENT_ORDER_SUCCESS("0020","下单成功"),
    REFUND_ORDER_STATUS("0021","退款请求失败"),
    REFUND_ORDER_SUCCESS("0022","退款操作成功"),
    REFUND_ORDER_ERROR("0023","订单退款异常"),
    REFUND_ORDER_PREPAYMENT("0024","此订单并未有预支付状态"),
    REFUND_ORDER_REPETITION("0025","退款申请重复"),
    REFUND_ORDER_WRONGAMOUNT("0026","退款金额大于预交费金额"),

    REQUEST_PARAM_EMPTY("9991","不能为空"),
    REQUEST_PARAM_ERROR("9992","参数无效"),
    REQUEST_SIGN_ERROR("9993","参数验签异常"),

    SYSTEM_ERROR("9998","系统异常,请稍后重试");

    private ExceptionEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    private String errorCode;
    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public boolean equalsErrorCode(String other) {
        return this.errorCode.equals(other);
    }
    public static String getByErrorCode(String errorCode){
        for(ExceptionEnum ex:ExceptionEnum.values()){
            if(ex.getErrorCode().equals(errorCode)){
                return ex.getErrorMsg();
            }
        }
        return null;
    }
}
