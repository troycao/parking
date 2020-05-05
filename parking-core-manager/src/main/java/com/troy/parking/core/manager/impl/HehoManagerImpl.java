package com.troy.parking.core.manager.impl;


import com.troy.parking.common.constants.SymbolConstant;
import com.troy.parking.common.exception.NotifyException;
import com.troy.parking.common.utils.HttpRequest;
import com.troy.parking.common.utils.JsonUtil;
import com.troy.parking.core.dao.ParkingOrderDao;
import com.troy.parking.core.dao.PaymentOrderDao;
import com.troy.parking.core.entity.ParkingOrderEntity;
import com.troy.parking.core.entity.PaymentOrderEntity;
import com.troy.parking.core.manager.GateManager;
import com.troy.parking.core.manager.IGateTokenService;
import com.troy.parking.core.manager.dto.*;
import com.troy.parking.core.manager.enums.NotifyEnum;
import com.troy.parking.core.manager.enums.PayTypeEnum;
import com.troy.parking.core.manager.enums.PaymentEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author troy
 * @date 2020/4/1
 **/
@Slf4j
@Component("heho")
public class HehoManagerImpl implements GateManager, IGateTokenService {

    @Autowired
    private PaymentOrderDao paymentOrderDao;

    @Autowired
    private ParkingOrderDao parkingOrderDao;

    @Override
    public PaymentEnum notify(GateNotifyDTO gateNotifyDTO) {
        PaymentOrderEntity paymentOrder = gateNotifyDTO.getPaymentOrder();
        String carNo = gateNotifyDTO.getCarNo();
        if(paymentOrder==null){
            log.info("{},支付订单不存在",carNo);
            return PaymentEnum.FAIL;
        }

        if(PayTypeEnum.SWEEPEDFEE.getCode().equals(paymentOrder.getType()) && StringUtils.isNotBlank(paymentOrder.getRedirectUri())){
            // 被扫，通知地址不为空，通过定时任务处理通知
            return null;
        }

        try {
            HehoNotiryReqDTO param = new HehoNotiryReqDTO();

            ParkingOrderEntity parkingOrder = parkingOrderDao.selectOneByCarNo(carNo);
            String channelOrder = parkingOrder.getPaymrntnr();
            param.setCarPayId(channelOrder);
            param.setPayState("00");
            param.setPayTxnTm(paymentOrder.getPayTime());

            HehoNotiryReqDTO.Pay  payList = param.new Pay();
            payList.setPayTm(paymentOrder.getPayTime());
            payList.setPayType(Optional.ofNullable(paymentOrder.getPayChannel())
                    .map(_type -> "05".equals(_type) ? "03" : _type)
                    .map(_type -> "06".equals(_type) ? "04" : _type)
                    .map(_type -> {
                        if ("03".equals(_type) || "04".equals(_type)) {
                            payList.setPayTxnNo(paymentOrder.getPayThirdChannleOrder());
                            return _type;
                        }
                        return null;
                    })
                    .orElseThrow(() -> new NotifyException(NotifyEnum.FAILED.getMsg()))
            );
            payList.setPayAmt(paymentOrder.getPayAmount());
            param.setPayList(Arrays.asList(payList));
            sign(param);
            String _param = JsonUtil.toJsonString(param);
            log.info("{},和皓支付通知结果请求：{}",carNo, _param);
            String response = HttpRequest.post_json(getChannelConfig().get("url") + "/api/car/paynotifycarorder", param);
            log.info("{},和皓支付通知结果返回： \n 请求：{} \n 返回：{}",carNo, _param, response);
            if (StringUtils.contains(response, "0000") && StringUtils.contains(response, "成功")) {
                return PaymentEnum.SUCCESS;
            }

        }catch (Exception e){
            log.error("和皓支付结果通知异常 [{}]", paymentOrder,  e);
        }
        return PaymentEnum.FAIL;
    }

    private void sign(HehoBaseReqDTO reqDTO){
        String key = null;
        if(reqDTO  instanceof AuztokenReqDTO){
            AuztokenReqDTO auztokenDTO = (AuztokenReqDTO) reqDTO;
            key = auztokenDTO.getTxnTokenId();
            auztokenDTO.setTxnTokenId(null);
        }else{

            AuztokenResDTO auztokenResDTO = JsonUtil.parseObject(getToken(),AuztokenResDTO.class);
            reqDTO.setTxnTokenId(auztokenResDTO.getTxnTokenId());
            key = auztokenResDTO.getTxnTokenCode();
        }

        Map<String, String> fieldsMap = getBeanToMapOrderAssic(reqDTO);
        reqDTO.setSignAture(sign(fieldsMap,key));
    }
    private Map<String, String> getBeanToMapOrderAssic(HehoBaseReqDTO reqDTO) {
        Map<String,String> fieldsMap = new TreeMap();
        for (Field declaredField : ArrayUtils.addAll(reqDTO.getClass().getDeclaredFields(),reqDTO.getClass().getSuperclass().getDeclaredFields())) {
            try {
                declaredField.setAccessible(true);
                if(!Objects.isNull(declaredField.get(reqDTO))){
                    if(declaredField.get(reqDTO) instanceof  List){
                        fieldsMap.put(declaredField.getName(), JsonUtil.toJsonString(reqDTO));
                    }else{
                        fieldsMap.put(declaredField.getName(), String.valueOf(declaredField.get(reqDTO)));
                    }
                }
            } catch (IllegalAccessException e) {
                log.error("和皓签名失败",e);
            }
        }
        return fieldsMap;
    }

    /**
     * 和皓报文签名
     * @param key
     */
    private String sign(Map<String,String> fieldsMap ,String key)  {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : fieldsMap.entrySet()) {

            if (StringUtils.isNotBlank(entry.getValue()) && !"signAture".equals(entry.getKey())) {
                sb
                        .append(entry.getKey())
                        .append(SymbolConstant.EQ)
                        .append(entry.getValue())
                        .append(SymbolConstant.AND)
                ;
            }
        }
        sb.append("txnTokenCode=").append(key);
        return DigestUtils.md5Hex(sb.toString());
    }

    @Override
    public String generateToken() {
        try {
            AuztokenReqDTO req = new AuztokenReqDTO();
            req.setAuzId(getChannelConfig().get("auzId"));
            req.setTxnTokenId(getChannelConfig().get("txnTokenCode"));
            sign(req);
            return HttpRequest.post_json(getUrl()+"/api/auz/auztoken",req);
        } catch (IOException e) {
            log.error("获取和皓token失败",e);
        }
        return null;
    }
    private String getUrl(){
        Map<String,String> config = getChannelConfig();
        if(config!=null){
            String url = config.get("url");
            if(StringUtils.isBlank(url)){
                log.info("和皓获取URL失败");
            }
            return url;
        }
        return null;
    }
}
