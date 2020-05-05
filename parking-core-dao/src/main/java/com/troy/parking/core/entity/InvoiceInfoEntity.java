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
@TableName("pims_invoice_info")
public class InvoiceInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String paymentOrderId;
	/**
	 * 
	 */
	private String invoiceType;
	/**
	 * 
	 */
	private String payerName;
	/**
	 * 
	 */
	private String payerTaxId;
	/**
	 * 
	 */
	private String payerPhone;
	/**
	 * 
	 */
	private String payeeName;
	/**
	 * 
	 */
	private String payeeTaxId;
	/**
	 * 
	 */
	private String payeeAddrPhone;
	/**
	 * 
	 */
	private String email;
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
	private String invoicePdfUrl;
	/**
	 * 
	 */
	private String invoiceSpUrl;
	/**
	 * 
	 */
	private String invoiceCode;
	/**
	 * 
	 */
	private String invoiceNo;
	/**
	 * 
	 */
	private String ticketOpenTime;
	/**
	 * 
	 */
	private String taxRate;
	/**
	 * 
	 */
	private String invoiceReqSeq;
	/**
	 * 
	 */
	private String refundId;
	/**
	 * 
	 */
	private String invoiceAmount;
	/**
	 * 
	 */
	private String taxAmount;
	/**
	 * 
	 */
	private String nonTaxAmount;
	/**
	 * 
	 */
	private String taxRateCode;
	/**
	 * 
	 */
	private String oriInvoiceCode;
	/**
	 * 
	 */
	private String oriInvoiceNo;
	/**
	 * 
	 */
	private Integer isDeleted;
	/**
	 * 
	 */
	private String invoiceStatus;
	/**
	 * 
	 */
	private String refundSeq;
	/**
	 * 
	 */
	private String checkCode;
	/**
	 * 
	 */
	private String xzstatus;
	/**
	 * 
	 */
	private String instatus;

}
