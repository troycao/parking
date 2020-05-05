package com.troy.parking.core.manager.impl;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.troy.parking.common.utils.DateUtil;
import com.troy.parking.core.dao.MerInfoDao;
import com.troy.parking.core.dao.ParkingOrderDao;
import com.troy.parking.core.dao.PaymentOrderDao;
import com.troy.parking.core.entity.MerInfoEntity;
import com.troy.parking.core.entity.ParkingOrderEntity;
import com.troy.parking.core.entity.PaymentOrderEntity;
import com.troy.parking.core.manager.ParkingManager;
import com.troy.parking.core.manager.PaymentManager;
import com.troy.parking.core.manager.dto.InitPaymentParamDTO;
import com.troy.parking.core.manager.dto.PaymentResultDTO;
import com.troy.parking.core.manager.dto.PaymentResultProcessDTO;
import com.troy.parking.core.manager.enums.InvoiceRateEnum;
import com.troy.parking.core.manager.enums.ParkingStatusEnum;
import com.troy.parking.core.manager.enums.PayTypeEnum;
import com.troy.parking.core.manager.enums.PaymentEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 2020/3/12 18:49
 * @auhor troy
 **/
@Slf4j
@Component
public class PaymentManagerImpl implements PaymentManager {

    @Autowired
    private MerInfoDao merInfoDao;

    @Autowired
    private PaymentOrderDao paymentOrderDao;

    @Autowired
    private ParkingOrderDao parkingOrderDao;

    @Autowired
    private ParkingManager parkingManager;

    /**
     * 初始化 支付信息入库
     * @param initPaymentParamDTO
     * @return
     */
    @Override
    public PaymentOrderEntity initPaymentOrder(InitPaymentParamDTO initPaymentParamDTO) {
        ParkingOrderEntity parkingOrder = initPaymentParamDTO.getParkingOrder();
        MerInfoEntity merInfo = merInfoDao.selectMerInfoByParkingId(parkingOrder.getParkingId());
        if (merInfo == null) {
            log.info("----停车费用支付异常, 商户信息异常 parkingName = {} carNo = {}, OUTORDERID = {}",
                    parkingOrder.getParkingName(),parkingOrder.getCarNo(),initPaymentParamDTO.getPaymentOrderId());
            return null;
        }
        PaymentOrderEntity paymentOrderEntity = new PaymentOrderEntity();
        paymentOrderEntity.setId(initPaymentParamDTO.getPaymentOrderId());
        paymentOrderEntity.setParkingOrderId(parkingOrder.getId());
        paymentOrderEntity.setPayAmount(String.valueOf(initPaymentParamDTO.getPayAmount()));
        paymentOrderEntity.setPayChannel(initPaymentParamDTO.getPayChannel());
        paymentOrderEntity.setStatus(PaymentEnum.PROCESS.getCode());
        paymentOrderEntity.setTotalAmount(String.valueOf(initPaymentParamDTO.getPayAmount()));
        paymentOrderEntity.setMId(merInfo.getMId());
        paymentOrderEntity.setTId(merInfo.getTId());
        paymentOrderEntity.setPayType(InvoiceRateEnum.RATE_LSTCF.getNum());
        paymentOrderEntity.setNotifyStatus(PaymentEnum.NEW.getCode());
        paymentOrderEntity.setPayTime(DateUtil.getCurrentDay(DatePattern.PURE_DATETIME_PATTERN));
        paymentOrderEntity.setType(initPaymentParamDTO.payType.getCode());
        paymentOrderEntity.setRedirectUri(initPaymentParamDTO.getNotifyUrl());
        // 初始化销账状态
        paymentOrderEntity.setXzStatus(PaymentEnum.NEW.getCode());
        log.info("支付流水 init:{}",paymentOrderEntity);
        boolean flag= paymentOrderDao.insert(paymentOrderEntity)>0;
        log.info("{},pimspaymentorder入库:{}",initPaymentParamDTO.getPaymentOrderId(),flag);
        return paymentOrderEntity;
    }

    @Override
    public List<PaymentOrderEntity> getUnclosedPayment() {
        //2小时前的时间
        Date datetime = DateUtil.addHour(new Date(),-2L);
        String limitTime = DateUtil.format(datetime,DatePattern.NORM_DATETIME_PATTERN);
        Wrapper wrapper = new LambdaQueryWrapper<PaymentOrderEntity>()
                .eq(PaymentOrderEntity::getStatus,PaymentEnum.PROCESS.getCode())
                .gt(PaymentOrderEntity::getCreateTime,limitTime);
        return paymentOrderDao.selectList(wrapper);
    }

    @Override
    public void paySuccessProcess(PaymentResultProcessDTO dto) {
        PaymentOrderEntity pimsPaymentOrder = dto.getPaymentOrderEntity();
        ParkingOrderEntity pimsparkingorder = parkingOrderDao.selectById(pimsPaymentOrder.getParkingOrderId());
        //TODO 开发票MQ
        if (PayTypeEnum.SWEEPEDFEE.getCode().equals(pimsPaymentOrder.getType())) {
            log.info("{},反扫成功，停车状态=已出场",pimsPaymentOrder.getId());
            parkingManager.updateParkingFeeAndStatus(pimsparkingorder.getId(), ParkingStatusEnum.YCC, pimsPaymentOrder.getPayAmount());
            //TODO 蓝盒后期使用MQ调用
            /*String serialNo = blueBoxBiz.getSerialNoByGateId(parkingOrder.getMachineId());
            blueBoxBiz.broadcastAndDisplay(serialNo, BroadcastAndDisplayEnum.PAY_SUCCESS);*/
        } else {
            log.info("{},支付成功，停车状态=出场中",pimsPaymentOrder.getId());
            //支付成功，停车状态=出场中
            pimsparkingorder.setStatus(ParkingStatusEnum.CCZ.getCode());
            parkingOrderDao.updateById(pimsparkingorder);
        }
        //TODO MQ  支付结果通知闸机
    }

    @Override
    public void payFailedProcess(PaymentResultProcessDTO dto) {
        PaymentOrderEntity paymentOrderEntity = dto.getPaymentOrderEntity();
        ParkingOrderEntity parkingOrderEntity = parkingOrderDao.selectById(paymentOrderEntity.getParkingOrderId());
        if (ParkingStatusEnum.ZFZ.equals(parkingOrderEntity.getStatus())) {
            log.info("支付失败，停车状态=已进场");
            //支付失败，停车状态=已进场
            parkingOrderEntity.setStatus(ParkingStatusEnum.YJC.getCode());
            parkingOrderDao.updateById(parkingOrderEntity);
        }
        if (PayTypeEnum.SWEEPEDFEE.getCode().equals(paymentOrderEntity.getType())) {
            log.info("反扫支付失败");
            //TODO 蓝盒后期使用MQ调用
            /*String serialNo = blueBoxBiz.getSerialNoByGateId(parkingOrder.getMachineId());
            blueBoxBiz.broadcastAndDisplay(serialNo, BroadcastAndDisplayEnum.PAY_FAILED, ScanOpt.START_SCAN);*/
        }
    }

    @Override
    public void updatePaymentResult(PaymentResultDTO dto) {
        PaymentOrderEntity pimsPaymentOrder = new PaymentOrderEntity();
        pimsPaymentOrder.setId(dto.getPaymentOrderId());
        pimsPaymentOrder.setPayChannleOrder(dto.getPayChannleOrder());
        pimsPaymentOrder.setStatus(dto.getStatus());
        pimsPaymentOrder.setPayThirdChannleOrder(dto.getPayThirdChannleOrder());
        pimsPaymentOrder.setUserId(dto.getUserId());
        log.info("更新支付流水:{}",pimsPaymentOrder);
        int count = paymentOrderDao.updateById(pimsPaymentOrder);
        log.info("{},更新支付流水:{}",pimsPaymentOrder.getId(),count);
    }
}
