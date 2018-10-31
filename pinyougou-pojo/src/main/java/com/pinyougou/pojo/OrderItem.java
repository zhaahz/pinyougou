package com.pinyougou.pojo;

import lombok.Data;

/**
 * OrderItem 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class OrderItem implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long itemId;
	private Long goodsId;
	private Long orderId;
	private String title;
	private String price;
	private Integer num;
	private String totalFee;
	private String picPath;
	private String sellerId;


}