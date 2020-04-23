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
@TableName("pims_gate_device_info")
public class GateDeviceInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String serialNo;
	/**
	 * 
	 */
	private String parkingId;
	/**
	 * 
	 */
	private String parkingName;
	/**
	 * 
	 */
	private String gateId;
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
	private String host;
	/**
	 * 
	 */
	private Integer port;
	/**
	 * 
	 */
	private Integer allowedAlarm;

}
