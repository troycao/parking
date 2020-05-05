package com.troy.parking.core.entity;

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
 * @date 2020-05-03 18:53:39
 */
@Data
@TableName("pims_payment_channel")
public class PaymentChannelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String channelName;
	/**
	 * 
	 */
	private String channelCode;
	/**
	 * 
	 */
	private String payType;
	/**
	 * 
	 */
	private Integer isUse;
	/**
	 * 
	 */
	private String payUrl;
	/**
	 * 
	 */
	private String createDatetime;
	/**
	 * 
	 */
	private String createBy;
	/**
	 * 
	 */
	private String updateDatetime;
	/**
	 * 
	 */
	private String updateBy;

}
