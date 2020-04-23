package com.troy.parking.order.dao;

import com.troy.parking.order.entity.PaymentOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author troy
 * @email troy@gmail.com
 * @date 2020-04-23 14:59:53
 */
@Mapper
public interface PaymentOrderDao extends BaseMapper<PaymentOrderEntity> {
	
}
