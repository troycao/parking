package com.troy.parking.core.manager.impl;

import cn.hutool.core.date.DatePattern;
import com.troy.parking.common.utils.DateUtil;
import com.troy.parking.common.utils.HttpRequest;
import com.troy.parking.common.utils.JsonUtil;
import com.troy.parking.common.utils.ReflectUtil;
import com.troy.parking.core.entity.PaymentOrderEntity;
import com.troy.parking.core.manager.GateManager;
import com.troy.parking.core.manager.IGateTokenService;
import com.troy.parking.core.manager.dto.GateNotifyDTO;
import com.troy.parking.core.manager.dto.SubinBaseReqDTO;
import com.troy.parking.core.manager.dto.SubinBaseResDTO;
import com.troy.parking.core.manager.dto.SubinNotifyReqDTO;
import com.troy.parking.core.manager.enums.PaymentEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author troy
 * @date 2020/4/10
 **/
@Slf4j
public class SubinManagerImpl  implements GateManager, IGateTokenService {

    /**获取停车费用接口*/
    public static final String METHOD_GETPRICE = "getpricenew";
    /**支付通知接口*/
    public static final String METHOD_PAY = "pay";
    /**进场通知*/
    public static final String METHOD_ENTRY = "EntryData";
    /**出场通知*/
    public static final String METHOD_EXIT = "ExitData";

    /**
     * 业务编号
     */
    public static final String ID = "123300145";

    /**
     * 停车费用查询方式 1:车牌
     */
    public static final String QUERY_WAY = "1";

    @Override
    public PaymentEnum notify(GateNotifyDTO gateNotifyDTO) throws IOException {
        String carNo = gateNotifyDTO.getCarNo();
        PaymentOrderEntity pimsPaymentOrder = gateNotifyDTO.getPaymentOrder();
        PaymentEnum result = PaymentEnum.FAIL;
        SubinNotifyReqDTO notifyRequest = new SubinNotifyReqDTO();
        notifyRequest.setId(ID);
        notifyRequest.setWay(QUERY_WAY);
        notifyRequest.setTicket(gateNotifyDTO.getCarNo().substring(1));
        notifyRequest.setAmount(pimsPaymentOrder.getPayAmount());
        notifyRequest.setDiscount("0");
        if (PaymentEnum.channel_wx.getCode().equals(pimsPaymentOrder.getPayChannel())) {
            notifyRequest.setSource("3");
        } else if (PaymentEnum.channel_zfb.getCode().equals(pimsPaymentOrder.getPayChannel())) {
            notifyRequest.setSource("4");
        }

        PaymentOrderEntity pimspaymentorder = new PaymentOrderEntity();
        pimspaymentorder.setId(pimsPaymentOrder.getId());
        log.info("=============== 支付结果异步通知SuBin请求信息: {}", JsonUtil.toJsonString(notifyRequest));
        Map<String,String> config = channelConfig.get();
        String channelKey = config.get("key");
        String channelUrl = config.get("url");

        SubinBaseReqDTO baseRequest = new SubinBaseReqDTO();
        baseRequest.setMethod(METHOD_PAY);
        baseRequest.setData(JsonUtil.toJsonString(notifyRequest));
        //组装请求参数
        String requestData = ReflectUtil.GetSignParams(baseRequest);
        String requestSignData = requestData + "&" + DateUtil.getCurrentDay(DatePattern.NORM_DATE_PATTERN) + "&" + channelKey;
        //MD5签名 32位
        baseRequest.setSign(DigestUtils.md5Hex(requestSignData));

        log.info("send request to subin, requestUrl = {}", channelUrl);
        log.info("send request to subin, requestJsonStr = {}", baseRequest);
        //发送postJson请求
        String responseJsonStr = null;
        responseJsonStr = HttpRequest.post_json(channelUrl, baseRequest);
        log.info("send to subin successfully, responseJsonStr = {}", responseJsonStr);
        if (StringUtils.isEmpty(responseJsonStr)) {
            return null;
        }
        SubinBaseResDTO baseResponse = JsonUtil.parseObject(responseJsonStr, SubinBaseResDTO.class);
        //响应参数校验
        if(!baseRequest.getRequesttoken().equals(baseResponse.getRequesttoken())){
            log.error("subin response resolved successfully, requestToken check failed");
            return null;
        }

        log.info("=============== 支付结果异步通知SuBin响应信息: {}", baseResponse);
        if (baseResponse != null && "0".equals(baseResponse.getCode())) {
            result = PaymentEnum.SUCCESS;
            log.info("=============== 支付结果异步通知SuBin结束 paymentOrderId = {}, carNo = {}, notifyStatus = success", pimsPaymentOrder.getId(), carNo);
        } else {
            result = PaymentEnum.ISNULL;
            log.warn("=============== 支付结果异步通知SuBin异常，速宾响应结果为空或token不一致 paymentOrderId = {}, carNo = {}, notifyStatus = fail", pimsPaymentOrder.getId(), carNo);
        }
        return result;
    }

    @Override
    public String generateToken() {
        return null;
    }
}
