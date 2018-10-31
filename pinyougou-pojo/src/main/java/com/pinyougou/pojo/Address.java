package com.pinyougou.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
/**
 * @Author ZhaJing
 * @Description //品牌实体类
 * @Date 15:08 2018/10/31
 * @version 1.0
 *
 **/
@Data
@Table(name="tb_address")
public class Address implements Serializable{

	private static final long serialVersionUID = -4689694958239207095L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="user_id")
    private String userId;
	@Column(name="province_id")
    private String provinceId;
	@Column(name="city_id")
    private String cityId;
	@Column(name="town_id")
    private String townId;
	@Column(name="mobile")
    private String mobile;
	@Column(name="address")
    private String address;
	@Column(name="contact")
    private String contact;
	@Column(name="is_default")
    private String isDefault;
	@Column(name="notes")
    private String notes;
	@Column(name="create_date")
    private Date createDate;
	@Column(name="alias")
    private String alias;


}