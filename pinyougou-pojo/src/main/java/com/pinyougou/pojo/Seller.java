package com.pinyougou.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
 
/**
 * 商家实体
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2017年12月14日 下午2:02:20
 * @version 1.0
 */
@Table(name="tb_seller")
@Data
public class Seller implements Serializable{
   
	private static final long serialVersionUID = -6636378626574886353L;
	/** 登录名 */
	@Id @Column(name="seller_id")
	private String sellerId;
	/** 公司名称 */
	@Column(name="name")
    private String name;
    /** 店铺名称 */
	@Column(name="nick_name")
    private String nickName;
    /** 登录密码 */
	@Column(name="password")
    private String password;
    /** 公司邮箱 */
	@Column(name="email")
    private String email;
    /** 手机 */
	@Column(name="mobile")
    private String mobile;
    /** 公司电话 */
	@Column(name="telephone")
    private String telephone;
    /** 状态 */
	@Column(name="status")
    private String status;
    /** 公司详细地址 */
	@Column(name="address_detail")
    private String addressDetail;
    /** 联系人姓名 */
	@Column(name="linkman_name")
    private String linkmanName;
    /** 联系人QQ */
	@Column(name="linkman_qq")
    private String linkmanQq;
    /** 联系人手机 */
	@Column(name="linkman_mobile")
    private String linkmanMobile;
    /** 联系人EMAIL */
	@Column(name="linkman_email")
    private String linkmanEmail;
    /** 营业执照号 */
	@Column(name="license_number")
    private String licenseNumber;
    /** 税务登记证号 */
	@Column(name="tax_number")
    private String taxNumber;
    /** 组织机构代码证 */
	@Column(name="org_number")
    private String orgNumber;
    /** 邮编 */
	@Column(name="address")
    private Long address;
    /** 公司LOGO */
	@Column(name="logo_pic")
    private String logoPic;
    /** 创建时间 */
	@Column(name="create_time")
    private Date createTime;
    /** 法定代表人 */
	@Column(name="legal_person")
    private String legalPerson;
    /** 法定代表人身份证号 */
	@Column(name="legal_person_card_id")
    private String legalPersonCardId;
    /** 开户行名称 */
	@Column(name="bank_name")
    private String bankName;
    /** 开户行支行 */
	@Column(name="bank_user")
    private String bankUser;
    /** 银行账号 */
	@Column(name="brief")
    private String brief;

}