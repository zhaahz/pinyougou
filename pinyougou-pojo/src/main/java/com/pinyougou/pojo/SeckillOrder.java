package com.pinyougou.pojo;

import lombok.Data;

/**
 * SeckillOrder 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class SeckillOrder implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long seckillId;
	private String money;
	private String userId;
	private String sellerId;
	private java.util.Date createTime;
	private java.util.Date payTime;
	private String status;
	private String receiverAddress;
	private String receiverMobile;
	private String receiver;
	private String transactionId;


}