package com.troy.parking.core.dao;

import com.troy.parking.core.entity.PaymentOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author troy
 * @email troy@gmail.com
 * @date 2020-05-03 18:53:39
 */
@Mapper
public interface PaymentOrderDao extends BaseMapper<PaymentOrderEntity> {
	
}