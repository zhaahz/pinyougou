package com.pinyougou.pojo;

import lombok.Data;

/**
 * GoodsDesc 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class GoodsDesc implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long goodsId;
	private String introduction;
	private String specificationItems;
	private String customAttributeItems;
	private String itemImages;
	private String packageList;
	private String saleService;


}