package com.troy.parking.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.Query;

import com.troy.parking.order.dao.PaymentChannelDao;
import com.troy.parking.order.entity.PaymentChannelEntity;
import com.troy.parking.order.service.PaymentChannelService;


@Service("paymentChannelService")
public class PaymentChannelServiceImpl extends ServiceImpl<PaymentChannelDao, PaymentChannelEntity> implements PaymentChannelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PaymentChannelEntity> page = this.page(
                new Query<PaymentChannelEntity>().getPage(params),
                new QueryWrapper<PaymentChannelEntity>()
        );

        return new PageUtils(page);
    }

}