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
@TableName("pims_payment_order")
public class PaymentOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String parkingOrderId;
	/**
	 * 
	 */
	private String payAmount;
	/**
	 * 
	 */
	private String payChannel;
	/**
	 * 
	 */
	private String status;
	/**
	 * 
	 */
	private String payChannleOrder;
	/**
	 * 
	 */
	private String createTime;
	/**
	 * 
	 */
	private String updateTime;
	/**
	 * 
	 */
	private String totalAmount;
	/**
	 * 
	 */
	private String couponDiscount;
	/**
	 * 
	 */
	private String pointDiscount;
	/**
	 * 
	 */
	private String point;
	/**
	 * 
	 */
	private String redirectUri;
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
	private String userId;
	/**
	 * 
	 */
	private String payType;
	/**
	 * 
	 */
	private String refundAmount;
	/**
	 * 
	 */
	private String payTime;
	/**
	 * 
	 */
	private String notifyStatus;
	/**
	 * 
	 */
	private String xzStatus;
	/**
	 * 
	 */
	private String refundId;
	/**
	 * 
	 */
	private String payThirdChannleOrder;
	/**
	 * 
	 */
	private String type;
	/**
	 * 
	 */
	private String orderNumber;
	/**
	 * 
	 */
	private String refundTime;
	/**
	 * 
	 */
	private String prePaymentAmount;
	/**
	 * 
	 */
	private String platformSeq;
	/**
	 * 
	 */
	private String serviceCharge;
	/**
	 * 
	 */
	private String resMsg;
	/**
	 * 
	 */
	private String refundReason;

}
