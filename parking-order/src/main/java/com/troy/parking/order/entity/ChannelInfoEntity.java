package com.troy.parking.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author troy
 * @email troy@gmail.com
 * @date 2020-04-23 14:59:54
 */
@Data
@TableName("pims_channel_info")
public class ChannelInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String channelKey;
	/**
	 * 
	 */
	private String channelValue;
	/**
	 * 
	 */
	private String createTime;
	/**
	 * 
	 */
	private String enable;
	/**
	 * 
	 */
	private String channel;

}
