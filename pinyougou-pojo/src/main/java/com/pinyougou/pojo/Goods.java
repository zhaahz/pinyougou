package com.pinyougou.pojo;

import lombok.Data;

/**
 * Goods 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class Goods implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String sellerId;
	private String goodsName;
	private Long defaultItemId;
	private String auditStatus;
	private String isMarketable;
	private Long brandId;
	private String caption;
	private Long category1Id;
	private Long category2Id;
	private Long category3Id;
	private String smallPic;
	private String price;
	private Long typeTemplateId;
	private String isEnableSpec;
	private String isDelete;


}