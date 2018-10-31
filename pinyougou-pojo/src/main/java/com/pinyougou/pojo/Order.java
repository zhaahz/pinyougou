package com.pinyougou.pojo;

import lombok.Data;

/**
 * Order 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class Order implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long orderId;
	private String payment;
	private String paymentType;
	private String postFee;
	private String status;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private java.util.Date paymentTime;
	private java.util.Date consignTime;
	private java.util.Date endTime;
	private java.util.Date closeTime;
	private String shippingName;
	private String shippingCode;
	private String userId;
	private String buyerMessage;
	private String buyerNick;
	private String buyerRate;
	private String receiverAreaName;
	private String receiverMobile;
	private String receiverZipCode;
	private String receiver;
	private java.util.Date expire;
	private String invoiceType;
	private String sourceType;
	private String sellerId;

}