package com.pinyougou.pojo;

import lombok.Data;

/**
 * FreightTemplate 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class FreightTemplate implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String sellerId;
	private String isDefault;
	private String name;
	private String sendTimeType;
	private String price;
	private java.util.Date createTime;


}