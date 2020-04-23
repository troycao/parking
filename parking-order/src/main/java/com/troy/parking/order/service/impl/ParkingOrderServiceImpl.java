package com.troy.parking.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.Query;

import com.troy.parking.order.dao.ParkingOrderDao;
import com.troy.parking.order.entity.ParkingOrderEntity;
import com.troy.parking.order.service.ParkingOrderService;


@Service("parkingOrderService")
public class ParkingOrderServiceImpl extends ServiceImpl<ParkingOrderDao, ParkingOrderEntity> implements ParkingOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ParkingOrderEntity> page = this.page(
                new Query<ParkingOrderEntity>().getPage(params),
                new QueryWrapper<ParkingOrderEntity>()
        );

        return new PageUtils(page);
    }

}