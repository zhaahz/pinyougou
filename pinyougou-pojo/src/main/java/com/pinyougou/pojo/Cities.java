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
/** 城市实体 */
@Table(name="tb_cities")
@Data
public class Cities implements Serializable{
 
	/** 主键id */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	/** 城市编号 */
	@Column(name="cityid")
    private String cityId;
	/** 城市名称 */
	@Column(name="city")
    private String city;
	/** 省份编号 */
	@Column(name="provinceid")
    private String provinceId;
}