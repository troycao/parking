package com.troy.parking.core.manager;

import com.troy.parking.core.entity.PaymentOrderEntity;
import com.troy.parking.core.manager.dto.InitPaymentParamDTO;
import com.troy.parking.core.manager.dto.PaymentResultDTO;
import com.troy.parking.core.manager.dto.PaymentResultProcessDTO;

import java.util.List;

public interface PaymentManager {
    /**
     * 初始化支付流水
     * @param initPaymentParamDTO
     * @return
     */
    PaymentOrderEntity initPaymentOrder(InitPaymentParamDTO initPaymentParamDTO);

    /**
     * 获取未闭环支付流水  两小时内处理中状态的订单
     * @return
     */
    List<PaymentOrderEntity> getUnclosedPayment();

    /**
     * 支付成功后处理
     * @param dto
     */
    void paySuccessProcess(PaymentResultProcessDTO dto);

    /**
     * 支付失败处理流程
     * @param dto
     */
    void payFailedProcess(PaymentResultProcessDTO dto);

    /**
     * 更新支付结果信息 支付状态 支付方流水号等
     * @param dto
     */
    void updatePaymentResult(PaymentResultDTO dto);
}
