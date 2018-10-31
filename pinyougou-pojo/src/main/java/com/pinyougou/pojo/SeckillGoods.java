package com.pinyougou.pojo;

import lombok.Data;

/**
 * SeckillGoods 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class SeckillGoods implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long goodsId;
	private Long itemId;
	private String title;
	private String smallPic;
	private String price;
	private String costPrice;
	private String sellerId;
	private java.util.Date createTime;
	private java.util.Date checkTime;
	private String status;
	private java.util.Date startTime;
	private java.util.Date endTime;
	private Integer num;
	private Integer stockCount;
	private String introduction;

}