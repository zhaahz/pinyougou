package com.pinyougou.pojo;

import lombok.Data;

/**
 * Seller 实体类
 * @date 2018-10-29 21:29:21
 * @version 1.0
 */
@Data
public class Seller implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String sellerId;
	private String name;
	private String nickName;
	private String password;
	private String email;
	private String mobile;
	private String telephone;
	private String status;
	private String addressDetail;
	private String linkmanName;
	private String linkmanQq;
	private String linkmanMobile;
	private String linkmanEmail;
	private String licenseNumber;
	private String taxNumber;
	private String orgNumber;
	private Long address;
	private String logoPic;
	private String brief;
	private java.util.Date createTime;
	private String legalPerson;
	private String legalPersonCardId;
	private String bankUser;
	private String bankName;


}