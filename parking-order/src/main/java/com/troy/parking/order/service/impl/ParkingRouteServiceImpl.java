package com.troy.parking.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.Query;

import com.troy.parking.order.dao.ParkingRouteDao;
import com.troy.parking.order.entity.ParkingRouteEntity;
import com.troy.parking.order.service.ParkingRouteService;


@Service("parkingRouteService")
public class ParkingRouteServiceImpl extends ServiceImpl<ParkingRouteDao, ParkingRouteEntity> implements ParkingRouteService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ParkingRouteEntity> page = this.page(
                new Query<ParkingRouteEntity>().getPage(params),
                new QueryWrapper<ParkingRouteEntity>()
        );

        return new PageUtils(page);
    }

}