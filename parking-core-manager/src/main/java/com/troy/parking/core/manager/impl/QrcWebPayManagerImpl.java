package com.troy.parking.core.manager.impl;

import com.troy.parking.common.constants.SymbolConstant;
import com.troy.parking.common.utils.CryptUtil;
import com.troy.parking.common.utils.HttpRequest;
import com.troy.parking.common.utils.XMLUtils;
import com.troy.parking.core.manager.QrcWebPayManager;
import com.troy.parking.core.manager.constants.QrcWebPayConstant;
import com.troy.parking.core.manager.dto.BarCodePayReqDTO;
import com.troy.parking.core.manager.dto.BarCodePayResDTO;
import com.troy.parking.core.manager.dto.DoQueryReqDTO;
import com.troy.parking.core.manager.dto.DoQueryResDTO;
import com.troy.parking.core.manager.enums.PaymentMethodEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Optional;

/**
 * 2020/4/13 14:34
 * @auhor troy
 **/
@Slf4j
@Component
@EnableConfigurationProperties({QrcWebPayConstant.class})
public class QrcWebPayManagerImpl implements QrcWebPayManager {

    @Autowired
    private QrcWebPayConstant qrcWebPayConstant;

    @Override
    public Optional<BarCodePayResDTO> onlineBarCodeDoPay(BarCodePayReqDTO barCodePayReqDTO) {
        String data = getData(barCodePayReqDTO);
        return doService(data, PaymentMethodEnum.ONLINE_BARCODE_DOPAY);
    }

    @Override
    public Optional<DoQueryResDTO> onlineDoQuery(DoQueryReqDTO doQueryReqDTO) {
        String data = getData(doQueryReqDTO);
        return doService(data, PaymentMethodEnum.ONLINE_DO_QUERY);
    }

    /**
     * 将DTO 加密 加签
     * @param obj
     * @return
     */
    private String getData(Object obj) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                sb.append(field.getName()).append(SymbolConstant.EQ).append(FieldUtils.readField(field, obj, true)).append(SymbolConstant.AND);
            }
            sb.deleteCharAt(sb.lastIndexOf(SymbolConstant.AND));
            log.debug("加密前字符串:{}", sb.toString());
            String signResult = DigestUtils.md5Hex(sb.toString().concat(qrcWebPayConstant.getSignKey()));
            sb.append(SymbolConstant.AND).append("sign=").append(signResult);
            log.info("signResult:{}", signResult);
        } catch (IllegalAccessException e) {
            log.error("获取参数失败", e);
            return null;
        }
        return sb.toString();
    }

    /**
     * 调用正反扫支付中心
     *
     * @param data
     * @param method
     * @param <E>
     * @return
     */
    private <E> Optional<E> doService(String data, PaymentMethodEnum method) {
        log.info("调用服务{},入参:{}", method.getServiceName(), data);
        String paramStr = CryptUtil.GetEncodeStr(data);
        String finalStr = Base64.encodeBase64String(paramStr.getBytes());
        StringBuilder sb = new StringBuilder("http://"+qrcWebPayConstant.getHost()+"/WebPay/online/");
        sb.append(method.getServiceName());
        String response;
        try {
            log.debug(finalStr);
            response = HttpRequest.post(sb.toString(), new HashMap() {{
                put("sText", finalStr);
            }});
            log.info("{}返回:{}",method.getServiceName(), response);
            E resEntity = (E) XMLUtils.fromXml(response, method.getResponseType());
            return Optional.of(resEntity);
        } catch (IOException e) {
            log.error("调用失败",e);
            return Optional.empty();
        }
    }
}
