package com.pinyougou.pojo;

import lombok.Data;

/**
 * TypeTemplate 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class TypeTemplate implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String specIds;
	private String brandIds;
	private String customAttributeItems;


}