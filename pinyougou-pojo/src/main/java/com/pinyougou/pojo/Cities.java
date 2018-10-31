package com.pinyougou.pojo;

import lombok.Data;

/**
 * Cities 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class Cities implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String cityid;
	private String city;
	private String provinceid;

}