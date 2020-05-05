package com.troy.parking.core.service.factory;

import com.troy.parking.common.exception.NotRouteException;
import com.troy.parking.common.utils.JsonUtil;
import com.troy.parking.core.api.ChannelInfoService;
import com.troy.parking.core.api.GateConfigService;
import com.troy.parking.core.api.GateService;
import com.troy.parking.core.dao.ParkingOrderDao;
import com.troy.parking.core.dao.PaymentOrderDao;
import com.troy.parking.core.entity.ParkingOrderEntity;
import com.troy.parking.core.entity.PaymentOrderEntity;
import com.troy.parking.core.manager.IGateConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class GateServiceFactory {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ParkingOrderDao parkingOrderDao;

    @Autowired
    PaymentOrderDao paymentOrderDao;

    @Autowired
    ChannelInfoService channelInfo;

    public GateService getGateServiceByCarNo(String carNo) throws NotRouteException {
        try {
            log.info("根据车牌号获取闸机实现类开始,车牌:", carNo);
            ParkingOrderEntity parkingOrder = Optional.ofNullable(parkingOrderDao.selectOneByCarNo(carNo)).orElseThrow(()->new NotRouteException());
            log.info("跟据车牌号获取闸机实现类,获取{}最新的停车订单:{}", carNo, JsonUtil.toJsonString(parkingOrder));
            String parkingName = parkingOrder.getParkingName();
            GateService gate =  getGateServiceByChannelParkingName(parkingName);
            log.info("跟据车牌号获取闸机实现类,结束");
            return gate;
        }catch (Exception e){
            log.error("跟据车牌号获取闸机实现类,失败;carNo = {}", carNo, e);
            throw new NotRouteException();
        }
    }

    public GateService getGateServiceByPaymentId(String paymentId) throws NotRouteException {
        try {
            PaymentOrderEntity payment = paymentOrderDao.selectById(paymentId);
            ParkingOrderEntity parking = parkingOrderDao.queryOrderByParkingId(payment.getParkingOrderId());
            String parkingName = parking.getParkingName();
            return getGateServiceByChannelParkingName(parkingName);
        } catch (Exception e) {
            log.error("获取闸机服务类失败 --- paymentId = {}", paymentId, e);
            throw new NotRouteException();
        }
    }

    public GateService getGateServiceByChannelParkingName(String parkingName) throws NotRouteException {
        log.info("根据闸机停车场名称获取闸机实现类,停车场名称:{}", parkingName);
        Map<String, String> config = channelInfo.getChannelConfigByParkingName(parkingName);
        String gateName = config.get(ChannelInfoService.GATE_NAME_KEY);
        log.info("根据闸机停车场名称获取闸机实现类,获取实现类名称{}", gateName);
        GateService gate = applicationContext.getBean(gateName, GateService.class);
        Optional.ofNullable(gate).orElseThrow(NotRouteException::new);
        if (gate instanceof GateConfigService) {
            ((IGateConfigService) gate).setChannelConfig(config);
        }
        log.info("根据闸机停车场名称获取闸机实现类,结束");
        return gate;
    }
}
