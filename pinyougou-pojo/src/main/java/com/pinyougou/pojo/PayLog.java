package com.pinyougou.pojo;

import lombok.Data;

/**
 * PayLog 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class PayLog implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String outTradeNo;
	private java.util.Date createTime;
	private java.util.Date payTime;
	private Long totalFee;
	private String userId;
	private String transactionId;
	private String tradeState;
	private String orderList;
	private String payType;


}