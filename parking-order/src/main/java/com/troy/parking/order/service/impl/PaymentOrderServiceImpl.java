package com.troy.parking.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.Query;

import com.troy.parking.order.dao.PaymentOrderDao;
import com.troy.parking.order.entity.PaymentOrderEntity;
import com.troy.parking.order.service.PaymentOrderService;


@Service("paymentOrderService")
public class PaymentOrderServiceImpl extends ServiceImpl<PaymentOrderDao, PaymentOrderEntity> implements PaymentOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PaymentOrderEntity> page = this.page(
                new Query<PaymentOrderEntity>().getPage(params),
                new QueryWrapper<PaymentOrderEntity>()
        );

        return new PageUtils(page);
    }

}