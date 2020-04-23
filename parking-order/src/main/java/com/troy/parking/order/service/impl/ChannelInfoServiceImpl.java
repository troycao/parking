package com.troy.parking.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.Query;

import com.troy.parking.order.dao.ChannelInfoDao;
import com.troy.parking.order.entity.ChannelInfoEntity;
import com.troy.parking.order.service.ChannelInfoService;


@Service("channelInfoService")
public class ChannelInfoServiceImpl extends ServiceImpl<ChannelInfoDao, ChannelInfoEntity> implements ChannelInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ChannelInfoEntity> page = this.page(
                new Query<ChannelInfoEntity>().getPage(params),
                new QueryWrapper<ChannelInfoEntity>()
        );

        return new PageUtils(page);
    }

}