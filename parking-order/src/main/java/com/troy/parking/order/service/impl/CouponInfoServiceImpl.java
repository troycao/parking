package com.troy.parking.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.Query;

import com.troy.parking.order.dao.CouponInfoDao;
import com.troy.parking.order.entity.CouponInfoEntity;
import com.troy.parking.order.service.CouponInfoService;


@Service("couponInfoService")
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoDao, CouponInfoEntity> implements CouponInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponInfoEntity> page = this.page(
                new Query<CouponInfoEntity>().getPage(params),
                new QueryWrapper<CouponInfoEntity>()
        );

        return new PageUtils(page);
    }

}