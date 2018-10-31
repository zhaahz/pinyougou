package com.pinyougou.pojo;

import lombok.Data;

/**
 * User 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class User implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String password;
	private String phone;
	private String email;
	private java.util.Date created;
	private java.util.Date updated;
	private String sourceType;
	private String nickName;
	private String name;
	private String status;
	private String headPic;
	private String qq;
	private String accountBalance;
	private String isMobileCheck;
	private String isEmailCheck;
	private String sex;
	private Integer userLevel;
	private Integer points;
	private Integer experienceValue;
	private java.util.Date birthday;
	private java.util.Date lastLoginTime;


}