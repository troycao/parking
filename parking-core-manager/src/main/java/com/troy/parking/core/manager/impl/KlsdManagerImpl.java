package com.troy.parking.core.manager.impl;

import cn.hutool.core.date.DatePattern;
import com.troy.parking.common.utils.DateUtil;
import com.troy.parking.common.utils.HttpRequest;
import com.troy.parking.common.utils.JsonUtil;
import com.troy.parking.common.utils.MoneyUtil;
import com.troy.parking.core.dao.ParkingOrderDao;
import com.troy.parking.core.entity.ParkingOrderEntity;
import com.troy.parking.core.entity.PaymentOrderEntity;
import com.troy.parking.core.manager.GateManager;
import com.troy.parking.core.manager.IGateTokenService;
import com.troy.parking.core.manager.dto.GateNotifyDTO;
import com.troy.parking.core.manager.dto.KlsdPayParkingFeeReqDTO;
import com.troy.parking.core.manager.enums.PaymentEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author troy
 * @date 2020/4/10
 **/

@Slf4j
public class KlsdManagerImpl implements GateManager, IGateTokenService {

    private final static int SUCCESS = 0;

    private String klsdUrl = "http://klsdzsf.granity.cn/";

    @Autowired
    private ParkingOrderDao parkingOrderDao;

    @Override
    public PaymentEnum notify(GateNotifyDTO gateNotifyDTO) throws IOException {
        PaymentOrderEntity pimsPaymentOrder = gateNotifyDTO.getPaymentOrder();
        if(pimsPaymentOrder==null){
            log.info("{},支付订单不存在",gateNotifyDTO.getCarNo());
            return PaymentEnum.FAIL;
        }

        ParkingOrderEntity parkingOrder = parkingOrderDao.selectOneByCarNo(gateNotifyDTO.getCarNo());
        KlsdPayParkingFeeReqDTO payParkingFeeReqDTO = new KlsdPayParkingFeeReqDTO();
        payParkingFeeReqDTO.setLpn(gateNotifyDTO.getCarNo());
        payParkingFeeReqDTO.setOrder_no(parkingOrder.getPaymrntnr());
        payParkingFeeReqDTO.setPay_money(MoneyUtil.yuanToFen(new BigDecimal(pimsPaymentOrder.getPayAmount())));
        payParkingFeeReqDTO.setPay_time(DateUtil.format(DateUtil.parse(pimsPaymentOrder.getPayTime(),DatePattern.PURE_DATETIME_PATTERN), DatePattern.NORM_DATETIME_PATTERN));
        String resStr = null;
        try {
            log.info("{},克立司帝停车费支付通知接口开始",gateNotifyDTO.getCarNo());
            resStr = HttpRequest.post(klsdUrl+"index.php/Api/Sxpay/notify.html",  JsonUtil.parseMap(JsonUtil.toJsonString(payParkingFeeReqDTO),String.class,Object.class));
            log.info("{},克立司帝停车费支付通知接口:{}",gateNotifyDTO.getCarNo(),resStr);
        } catch (IOException e) {
            log.error("克立司帝停车费支付通知异常"+gateNotifyDTO.getCarNo(),e);
            return PaymentEnum.FAIL;
        }
        if("success".equals(resStr)){
            return PaymentEnum.SUCCESS;
        }
        return PaymentEnum.FAIL;
    }

    @Override
    public String generateToken() {
        return null;
    }
}
