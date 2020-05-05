package com.troy.parking.core.manager.constants;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 2020/3/10 0:27
 * @auhor troy
 **/
@Data
@ConfigurationProperties(prefix = "qrcWebPay")
@ConditionalOnProperty(prefix = "qrcWebPay")
public class QrcWebPayConstant {
    private String signKey;
    private String host;

    private String paymentUrl;

    private String orderType;

    private String callbackNotify;

}
