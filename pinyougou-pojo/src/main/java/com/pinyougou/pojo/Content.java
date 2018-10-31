package com.pinyougou.pojo;

import lombok.Data;

/**
 * Content 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class Content implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long categoryId;
	private String title;
	private String url;
	private String pic;
	private String status;
	private Integer sortOrder;


}