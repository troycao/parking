package com.troy.parking.core.dao;

import com.troy.parking.core.entity.ChannelInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author troy
 * @email troy@gmail.com
 * @date 2020-05-03 18:53:39
 */
@Mapper
public interface ChannelInfoDao extends BaseMapper<ChannelInfoEntity> {

    public List<ChannelInfoEntity> selectChannelInfoByParkingName(String parkingName);
	
}
