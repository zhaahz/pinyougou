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
@Table(name="tb_goods_desc")
@Data
public class GoodsDesc implements Serializable{
	private static final long serialVersionUID = -7405298140497362200L;
	@Id @Column(name="goods_id")
	private Long goodsId;
	@Column(name="introduction")
    private String introduction;
	@Column(name="specification_items")
    private String specificationItems;
	@Column(name="custom_attribute_items")
    private String customAttributeItems;
	@Column(name="item_images")
    private String itemImages;
	@Column(name="package_list")
    private String packageList;
	@Column(name="sale_service")
    private String saleService;

 }