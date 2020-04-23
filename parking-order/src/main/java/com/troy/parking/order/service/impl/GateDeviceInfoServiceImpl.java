package com.troy.parking.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.Query;

import com.troy.parking.order.dao.GateDeviceInfoDao;
import com.troy.parking.order.entity.GateDeviceInfoEntity;
import com.troy.parking.order.service.GateDeviceInfoService;


@Service("gateDeviceInfoService")
public class GateDeviceInfoServiceImpl extends ServiceImpl<GateDeviceInfoDao, GateDeviceInfoEntity> implements GateDeviceInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GateDeviceInfoEntity> page = this.page(
                new Query<GateDeviceInfoEntity>().getPage(params),
                new QueryWrapper<GateDeviceInfoEntity>()
        );

        return new PageUtils(page);
    }

}