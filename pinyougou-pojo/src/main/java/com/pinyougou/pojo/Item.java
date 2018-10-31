package com.pinyougou.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
/**
 * @Author ZhaJing
 * @Description //品牌实体类
 * @Date 15:08 2018/10/31
 * @version 1.0
 *
 **/
@Table(name="tb_item")
@Data
public class Item implements Serializable{
    
	private static final long serialVersionUID = -8450461374139999455L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="title")
    private String title;
	@Column(name="sell_point")
    private String sellPoint;
	@Column(name="price")
    private BigDecimal price;
	@Column(name="stock_count")
    private Integer stockCount;
	@Column(name="num")
    private Integer num;
	@Column(name="barcode")
    private String barcode;
	@Column(name="image")
    private String image;
	@Column(name="categoryid")
    private Long categoryid;
	@Column(name="status")
    private String status;
	@Column(name="create_time")
    private Date createTime;
	@Column(name="update_time")
    private Date updateTime;
	@Column(name="item_sn")
    private String itemSn;
	@Column(name="cost_pirce")
    private BigDecimal costPirce;
	@Column(name="market_price")
    private BigDecimal marketPrice;
	@Column(name="is_default")
    private String isDefault;
	@Column(name="goods_id")
    private Long goodsId;
	@Column(name="seller_id")
    private String sellerId;
	@Column(name="cart_thumbnail")
    private String cartThumbnail;
	@Column(name="category")
    private String category;
	@Column(name="brand")
    private String brand;
	@Column(name="spec")
    private String spec;
	@Column(name="seller")
    private String seller;

  }