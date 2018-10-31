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
/** 区域实体*/
@Table(name="tb_areas")
@Data
public class Areas implements Serializable{

    /** 主键id */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	/** 区域id */
	@Column(name="areaid")
    private String areaId;
	/** 区域名称 */
	@Column(name="area")
    private String area;
	/** 城市id */
	@Column(name="cityid")
    private String cityId;


}