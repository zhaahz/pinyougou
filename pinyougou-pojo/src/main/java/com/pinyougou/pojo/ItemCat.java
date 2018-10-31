package com.pinyougou.pojo;

import lombok.Data;

/**
 * ItemCat 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class ItemCat implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long parentId;
	private String name;
	private Long typeId;

}