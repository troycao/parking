package com.troy.parking.core.manager.impl;

import com.troy.parking.common.constants.SymbolConstant;
import com.troy.parking.common.utils.DateUtil;
import com.troy.parking.common.utils.HttpRequest;
import com.troy.parking.common.utils.MD5Util;
import com.troy.parking.core.manager.QrcThirdPayManager;
import com.troy.parking.core.manager.constants.QrcWebPayConstant;
import com.troy.parking.core.manager.dto.AppletPayRequestDto;
import com.troy.parking.core.manager.dto.AppletPayResponseDto;
import com.troy.parking.core.manager.dto.TableCardScanPayRequestDto;
import com.troy.parking.core.manager.enums.PaymentMethodEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@EnableConfigurationProperties({QrcWebPayConstant.class})
public class QrcThirdPayManagerImpl implements QrcThirdPayManager {

    @Autowired
    private QrcWebPayConstant qrcWebPayConstant;

    @Override
    public Optional<String> tableCardScanPay(TableCardScanPayRequestDto tableCardScanPayRequestDto) {

        String paramStr = "instId=%s&mid=%s&tid=%s&outOrderId=%s&orderType=%s&payAmount=%s&orderDate=%s&orderTime=%s&notifyUrl=%s";
        String param = String.format(paramStr,
                tableCardScanPayRequestDto.getInstId(),
                tableCardScanPayRequestDto.getMId(),
                tableCardScanPayRequestDto.getTId(),
                tableCardScanPayRequestDto.getPaymentOrderId(),
                tableCardScanPayRequestDto.getOrderType(),
                tableCardScanPayRequestDto.getPayAmount(),
                DateUtil.getCurrentDay("yyyyMMdd"),
                DateUtil.getCurrentTime("HHmmss"),
                qrcWebPayConstant.getCallbackNotify());

        String resultUrl = "";
        try {
            resultUrl = qrcWebPayConstant.getPaymentUrl() + "?" + MD5Util.getMD532Sign(param)
                    + SymbolConstant.AND + "PLATE=" + tableCardScanPayRequestDto.getCarNo()
                    + SymbolConstant.AND + "PARKSTARTTIME=" + tableCardScanPayRequestDto.getParkStartTime()
                    + SymbolConstant.AND + "LOTID=" + tableCardScanPayRequestDto.getParkingId();
            return Optional.of(resultUrl);
        } catch (Exception e) {
            log.error("台牌埋参失败", e);
            resultUrl = qrcWebPayConstant.getPaymentUrl() + "?" + MD5Util.getMD532Sign(param);
            return Optional.of(resultUrl);
        }
    }

    @Override
    public Optional<AppletPayResponseDto> appletPay(AppletPayRequestDto appletPayRequestDto) {
        if (appletPayRequestDto == null){
            log.error("支付信息为空");
            return Optional.empty();
        }

        AppletPayResponseDto appletPayResponse = null;
        try {
            StringBuilder payUrl = new StringBuilder("http://"+qrcWebPayConstant.getHost()+"/WebPay/online/");
            payUrl.append(PaymentMethodEnum.ONLINE_APPLET_DOPAY.getServiceName());

            String paymentResponse = HttpRequest.post_json(payUrl.toString(), appletPayRequestDto);
            appletPayResponse = null;//BeanUtils.json2Object(paymentResponse, AppletPayResponseDto.class);
            log.info("请求小程序支付返回结果 ---{}", appletPayResponse);
        } catch (IOException e) {
            log.error("请求小程序支付异常", e);
        }
        return Optional.of(appletPayResponse);
    }
}
