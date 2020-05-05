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
@TableName("pims_box_error_history")
public class BoxErrorHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String parkingName;
	/**
	 * 
	 */
	private String gateName;
	/**
	 * 
	 */
	private String boxName;
	/**
	 * 
	 */
	private String heartBeatStatus;
	/**
	 * 
	 */
	private String heartBeatErrorTime;
	/**
	 * 
	 */
	private String deviceStatus;
	/**
	 * 
	 */
	private String deviceStatusErrorTime;
	/**
	 * 
	 */
	private String createdTime;
	/**
	 * 
	 */
	private String serialNo;

}
