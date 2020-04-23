package com.troy.parking.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.Query;

import com.troy.parking.order.dao.InvoiceInfoDao;
import com.troy.parking.order.entity.InvoiceInfoEntity;
import com.troy.parking.order.service.InvoiceInfoService;


@Service("invoiceInfoService")
public class InvoiceInfoServiceImpl extends ServiceImpl<InvoiceInfoDao, InvoiceInfoEntity> implements InvoiceInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InvoiceInfoEntity> page = this.page(
                new Query<InvoiceInfoEntity>().getPage(params),
                new QueryWrapper<InvoiceInfoEntity>()
        );

        return new PageUtils(page);
    }

}