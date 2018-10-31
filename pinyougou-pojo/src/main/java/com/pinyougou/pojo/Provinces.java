package com.pinyougou.pojo;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;
/**
 * @Author ZhaJing
 * @Description //品牌实体类
 * @Date 15:08 2018/10/31
 * @version 1.0
 *
 **/
/** 省份实体 */
@Table(name="tb_provinces")
@Data
public class Provinces implements Serializable{
    
	private static final long serialVersionUID = -4527956140012506847L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="provinceid")
    private String provinceId;
	@Column(name="province")
    private String province;

}