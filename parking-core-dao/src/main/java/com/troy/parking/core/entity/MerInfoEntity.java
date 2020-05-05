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
@TableName("pims_mer_info")
public class MerInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String parkingName;
	/**
	 * 
	 */
	private String mId;
	/**
	 * 
	 */
	private String tId;
	/**
	 * 
	 */
	private String merName;
	/**
	 * 
	 */
	private String fieldA;
	/**
	 * 
	 */
	private String fieldB;
	/**
	 * 
	 */
	private String fieldC;
	/**
	 * 
	 */
	private String quartersId;
	/**
	 * 
	 */
	private String parkingId;
	/**
	 * 
	 */
	private String channel;

}
