package com.pinyougou.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * @Author ZhaJing
 * @Description //品牌实体类
 * @Date 15:52 2018/10/31
 * @version 1.0
 *
 **/
@Table(name="tb_user")
@Data
public class User implements Serializable{
    
	private static final long serialVersionUID = -743836578906316194L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="username")
    private String username;
	@Column(name="password")
    private String password;
	@Column(name="phone")
    private String phone;
	@Column(name="email")
    private String email;
	@Column(name="created")
    private Date created;
	@Column(name="updated")
    private Date updated;
	@Column(name="source_type")
    private String sourceType;
	@Column(name="nick_name")
    private String nickName;
	@Column(name="name")
    private String name;
	@Column(name="status")
    private String status;
	@Column(name="head_pic")
    private String headPic;
	@Column(name="qq")
    private String qq;
	@Column(name="account_balance")
    private Long accountBalance;
	@Column(name="is_mobile_check")
    private String isMobileCheck;
	@Column(name="is_email_check")
    private String isEmailCheck;
	@Column(name="sex")
    private String sex;
	@Column(name="user_level")
    private Integer userLevel;
	@Column(name="points")
    private Integer points;
	@Column(name="experience_value")
    private Integer experienceValue;
	@Column(name="birthday")
    private Date birthday;
	@Column(name="last_login_time")
    private Date lastLoginTime;

}