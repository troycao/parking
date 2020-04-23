package com.troy.parking.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.order.entity.GateDeviceInfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author troy
 * @email troy@gmail.com
 * @date 2020-04-23 14:59:54
 */
public interface GateDeviceInfoService extends IService<GateDeviceInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

