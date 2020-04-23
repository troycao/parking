package com.troy.parking.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.order.entity.PaymentOrderEntity;

import java.util.Map;

/**
 * 
 *
 * @author troy
 * @email troy@gmail.com
 * @date 2020-04-23 14:59:53
 */
public interface PaymentOrderService extends IService<PaymentOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

