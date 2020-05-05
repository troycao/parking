package com.troy.parking.core.service.impl;

import com.troy.parking.common.Response;
import com.troy.parking.common.enums.ExceptionEnum;
import com.troy.parking.common.exception.NotRouteException;
import com.troy.parking.common.utils.JsonUtil;
import com.troy.parking.core.api.GateService;
import com.troy.parking.core.api.bo.ParkingFeeRequestBO;
import com.troy.parking.core.api.bo.ParkingFeeResponseBO;
import com.troy.parking.core.dao.ParkingOrderDao;
import com.troy.parking.core.entity.ParkingOrderEntity;
import com.troy.parking.core.manager.ParkingManager;
import com.troy.parking.core.manager.dto.GateChannelRespDTO;
import com.troy.parking.core.manager.dto.ParkingInfoDTO;
import com.troy.parking.core.manager.enums.ParkingStatusEnum;
import com.troy.parking.core.manager.enums.PaymentEnum;
import com.troy.parking.core.service.factory.GateServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
public class ParkingServiceImpl  {

    @Autowired
    ParkingManager parkingManager;

    @Autowired
    GateService gateService;

    @Autowired
    GateServiceFactory gateServiceFactory;

    @Autowired
    ParkingOrderDao parkingOrderDao;

    public Response<ParkingFeeResponseBO> checkingParkingFee(ParkingFeeRequestBO parkingFeeRequestBO) {
        log.info("查询停车费用,开始调用闸机:{}", parkingFeeRequestBO.getCarNo());
        ParkingFeeResponseBO response = null;
        GateService gateService = null;

        try {
            gateService = gateServiceFactory.getGateServiceByCarNo(parkingFeeRequestBO.getCarNo());
            response = gateService.getParkingFee(parkingFeeRequestBO.getCarNo());
            log.info("查询停车费用,闸机返回...  [{}]", JsonUtil.toJsonString(response));
        } catch (NotRouteException e) {
            log.error("----查询停车费用,获取停车费用异常 -- {}", e.getMessage());
            return Response.failed(ExceptionEnum.PARKING_FEE_EMPTY.getErrorMsg());
        }

        if (response == null) {
            log.error("----查询停车费用,获取停车费用异常 -- {}", JsonUtil.toJsonString(response));
            return Response.failed(ExceptionEnum.QUERY_PARKING_FEE_ERROR.getErrorMsg());
        }
        //停车费用查询异常
        if (!response.getResult()) {
            log.error("----查询停车费用,取停车费用异常 result = {}, message = {}", response.getResult(), response.getMessage());
            if(org.apache.commons.lang3.StringUtils.isBlank(response.getMessage())){
                return Response.failed(ExceptionEnum.QUERY_PARKING_FEE_ERROR.getErrorCode());
            }
            return Response.failed(response.getMessage());
        }

        response.setTicket(Optional.ofNullable(response.getTicket()).filter(org.apache.commons.lang3.StringUtils::isNotBlank).filter(car -> car.equals(parkingFeeRequestBO.getCarNo())).orElse(parkingFeeRequestBO.getCarNo()));

        ParkingInfoDTO dto = new ParkingInfoDTO();
        GateChannelRespDTO gateChannelRespDTO = new GateChannelRespDTO();
        BeanUtils.copyProperties(response,gateChannelRespDTO);
        dto.setParkingFeeResponse(gateChannelRespDTO);

        try {
            ParkingOrderEntity laterOrder = Optional.ofNullable(parkingOrderDao.searchParkingOrderByGateOrderId(response.getGateOrderId())).orElseThrow(new );
            if (ParkingStatusEnum.YJC.equals(laterOrder.getStatus())) {
                //未支付，返回费用
                dto.setStatus(PaymentEnum.FAIL.getCode());

                updateParkingOrder(dto, laterOrder);
                log.info("查询停车费用，停车订单号：{}  ---  车牌：{} ， 状态：{} ，待支付{}", laterOrder.getId(), laterOrder.getCarNo(), ParkingStatusEnum.YJC.getMsg(), laterOrder.getParkingFee());
                return returnParkingInfo(dto, laterOrder);
            } else if (ParkingStatusEnum.ZFZ.equals(laterOrder.getStatus())) {
                List<PimsPaymentOrder> orders = paymentOrderService.searchPaymentOrderByParkingId(laterOrder.getId());
                if(orders == null || orders.size() <= 0){
                    //未找支付订单，停车订单状态异常，未支付，返回费用
                    dto.setStatus(PaymentEnum.FAIL.getCode());
                    updateParkingOrderStatus(laterOrder.getId(), ParkingStatusEnum.YJC.getCode());
                    log.info("查询停车费用，停车订单号：{}  ---  车牌：{} ， 状态：{} --> YJC，待支付{}", laterOrder.getId(), laterOrder.getCarNo(), ParkingStatusEnum.YJC.getMsg(), laterOrder.getParkingFee());
                    return returnParkingInfo(dto, laterOrder);
                }else if(orders.stream().filter(_o -> PaymentEnum.PROCESS.getCode().equals(_o.getStatus()) ).count() > 0){
                    //支付中，未通知闸机，费用不变，返回支付处理中
                    log.info("查询停车费用，停车订单号：{}  ---  车牌：{} ， 状态：{} ", laterOrder.getId(), laterOrder.getCarNo(), ParkingStatusEnum.ZFZ.getMsg());
                    dto.setStatus(PaymentEnum.PROCESS.getCode());
                    return returnParkingInfo(dto, laterOrder);
                }else if(orders.stream().filter(_o ->  PaymentEnum.SUCCESS.getCode().equals(_o.getStatus())).count() > 0){
                    dto.setStatus(PaymentEnum.FAIL.getCode());
                    updateParkingOrderStatus(laterOrder.getId(), ParkingStatusEnum.CCZ.getCode());
                    log.info("查询停车费用，停车订单号：{}  ---  车牌：{} ， 状态：{} --> CCZ，待支付{}", laterOrder.getId(), laterOrder.getCarNo(), ParkingStatusEnum.YJC.getMsg(), laterOrder.getParkingFee());
                    return returnParkingInfo(dto, laterOrder);
                }else{
                    dto.setStatus(PaymentEnum.FAIL.getCode());
                    updateParkingOrderStatus(laterOrder.getId(), ParkingStatusEnum.YJC.getCode());
                    log.info("查询停车费用，停车订单号：{}  ---  车牌：{} ， 状态：{} --> YJC，待支付{}", laterOrder.getId(), laterOrder.getCarNo(), ParkingStatusEnum.YJC.getMsg(), laterOrder.getParkingFee());
                    return returnParkingInfo(dto, laterOrder);
                }
            } else if (ParkingStatusEnum.CCZ.equals(laterOrder.getStatus())) {
                if(new BigDecimal(response.getPrice()).compareTo(BigDecimal.ZERO) == 0){
                    //未产生费用
                    PaymentOrderInvoice paymentOrderInvoice = paymentOrderService.querySucceedPaymentOrderByParkingId(laterOrder.getId());
                    return returnParkingFee(response, requestDTO, paymentOrderInvoice);
                }

                if (isNotify(laterOrder)) {
                    //出场中，支付订单都已通知闸机
                    if(gateService.disposeGateChannelResponse(response)){
                        //闸机方判断，超时，重新记费，返回费用
                        if (identicalFee(laterOrder)) {
                            //费用相同，都已支付,增加新产生的费用
                            updateParkingOrder(dto, laterOrder);
                        }

                        dto.setStatus(PaymentEnum.FAIL.getCode());
                        return returnParkingInfo(dto, laterOrder);
                    }else{
                        //TODO 应该 返回所有订单的发票，这里只返回最新成功的发票
                        PaymentOrderInvoice paymentOrderInvoice = paymentOrderService.querySucceedPaymentOrderByParkingId(laterOrder.getId());
                        return returnParkingFee(response, requestDTO, paymentOrderInvoice);
                    }
                } else {
                    //出场中，通知闸机失败，费用重复

                    dto.setStatus(PaymentEnum.PROCESS.getCode());
                    return returnParkingInfo(dto, laterOrder);
                }
            } else {
                //已出场，还查到费用，入场信息不存在
                log.info("查询停车费用，停车订单号：{}  ---  车牌：{} ， 状态：{} ", laterOrder.getId(), laterOrder.getCarNo(), ParkingStatusEnum.YCC.getMsg());
                return ExceptionEnum.PARKING_FEE_EMPTY.getResponse();
            }
        } catch (Exception e) {
            log.error("查询停车费用异常, 车牌"+ requestDTO.getCarNo(),  e);
            return ExceptionEnum.QUERY_PARKING_FEE_ERROR.getResponse();
        }
        return null;
    }
}
