package com.troy.parking.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.Query;

import com.troy.parking.order.dao.MerInfoDao;
import com.troy.parking.order.entity.MerInfoEntity;
import com.troy.parking.order.service.MerInfoService;


@Service("merInfoService")
public class MerInfoServiceImpl extends ServiceImpl<MerInfoDao, MerInfoEntity> implements MerInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MerInfoEntity> page = this.page(
                new Query<MerInfoEntity>().getPage(params),
                new QueryWrapper<MerInfoEntity>()
        );

        return new PageUtils(page);
    }

}