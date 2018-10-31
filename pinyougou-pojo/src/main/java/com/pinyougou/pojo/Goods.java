package com.pinyougou.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;
/**
 * 商品实体
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2017年12月19日 下午11:44:19
 * @version 1.0
 */
@Table(name="tb_goods")
@Data
public class Goods implements Serializable{

	private static final long serialVersionUID = -3888154864571208139L;
	/** 主键 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	/** 商家id */
	@Column(name="seller_id")
    private String sellerId;
    /** SPU名称 */
	@Column(name="goods_name")
    private String goodsName;
    /** 默认SKU */
	@Column(name="default_item_id")
    private Long defaultItemId;
    /** 状态 */
	@Column(name="audit_status")
    private String auditStatus;
    /** 是否上架 */
	@Column(name="is_marketable")
    private String isMarketable;
    /** 品牌id */
	@Column(name="brand_id")
    private Long brandId;
    /** 副标题 */
	@Column(name="caption")
    private String caption;
    /** 一级分类 */
	@Column(name="category1_id")
    private Long category1Id;
    /** 二级分类 */
	@Column(name="category2_id")
    private Long category2Id;
    /** 三级分类 */
	@Column(name="category3_id")
    private Long category3Id;
    /** 小图 */
	@Column(name="small_pic")
    private String smallPic;
    /** 商城价 */
	@Column(name="price")
    private BigDecimal price;
    /** 分类模版id */
	@Column(name="type_template_id")
    private Long typeTemplateId;
    /** 是否启用规格 */
	@Column(name="is_enable_spec")
    private String isEnableSpec;
    /** 是否删除 */
	@Column(name="is_delete")
    private String isDelete;
    /** 商品描述 */
	@Transient
    private GoodsDesc goodsDesc;
    /** 商品SKU列表 */
	@Transient
    private List<Item> items;
}