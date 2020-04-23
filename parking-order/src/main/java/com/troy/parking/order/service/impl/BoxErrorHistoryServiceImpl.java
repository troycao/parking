package com.troy.parking.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.Query;

import com.troy.parking.order.dao.BoxErrorHistoryDao;
import com.troy.parking.order.entity.BoxErrorHistoryEntity;
import com.troy.parking.order.service.BoxErrorHistoryService;


@Service("boxErrorHistoryService")
public class BoxErrorHistoryServiceImpl extends ServiceImpl<BoxErrorHistoryDao, BoxErrorHistoryEntity> implements BoxErrorHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BoxErrorHistoryEntity> page = this.page(
                new Query<BoxErrorHistoryEntity>().getPage(params),
                new QueryWrapper<BoxErrorHistoryEntity>()
        );

        return new PageUtils(page);
    }

}