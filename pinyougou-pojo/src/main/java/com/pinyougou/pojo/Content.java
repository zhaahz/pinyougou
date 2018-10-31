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
 * 广告实体
 */
@Table(name="tb_content")
@Data
public class Content implements Serializable{
   
	private static final long serialVersionUID = 6081205748121801261L;
	/** 主键id */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	/** 广告分类id */
	@Column(name="category_id")
    private Long categoryId;
	/** 广告标题 */
	@Column(name="title")
    private String title;
	/** 广告链接 */
	@Column(name="url")
    private String url;
	/** 图片地址 */
	@Column(name="pic")
    private String pic;
	/** 状态 */
	@Column(name="status")
    private String status = "0";
	/** 排序 */
	@Column(name="sort_order")
    private Integer sortOrder;
}