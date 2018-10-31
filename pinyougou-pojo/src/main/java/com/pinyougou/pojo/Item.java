package com.pinyougou.pojo;

import lombok.Data;

/**
 * Item 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class Item implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String title;
	private String sellPoint;
	private String price;
	private Integer stockCount;
	private Integer num;
	private String barcode;
	private String image;
	private Long categoryid;
	private String status;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private String itemSn;
	private String costPirce;
	private String marketPrice;
	private String isDefault;
	private Long goodsId;
	private String sellerId;
	private String cartThumbnail;
	private String category;
	private String brand;
	private String spec;
	private String seller;

}