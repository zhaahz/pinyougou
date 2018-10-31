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
/**
 * 广告分类
 */
@Table(name="tb_content_category")
@Data
public class ContentCategory implements Serializable{
    
	private static final long serialVersionUID = 5930846169505417346L;
	/** 主键 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	/** 广告分类名称 */
	@Column(name="name")
    private String name;

}