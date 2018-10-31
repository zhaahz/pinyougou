package com.pinyougou.pojo;

import lombok.Data;

/**
 * SpecificationOption 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class SpecificationOption implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String optionName;
	private Long specId;
	private Integer orders;

}