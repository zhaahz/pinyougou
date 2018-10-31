package com.pinyougou.pojo;

import lombok.Data;

/**
 * Address 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class Address implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String userId;
	private String provinceId;
	private String cityId;
	private String townId;
	private String mobile;
	private String address;
	private String contact;
	private String isDefault;
	private String notes;
	private java.util.Date createDate;
	private String alias;

}