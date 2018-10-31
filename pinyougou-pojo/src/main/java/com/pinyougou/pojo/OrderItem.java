package com.pinyougou.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
/**
 * @Author ZhaJing
 * @Description //品牌实体类
 * @Date 15:08 2018/10/31
 * @version 1.0
 *
 **/
@Table(name="tb_order_item")
@Data
public class OrderItem implements Serializable{
  
	private static final long serialVersionUID = -1486894002218647239L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="item_id")
    private Long itemId;
	@Column(name="goods_id")
    private Long goodsId;
	@Column(name="order_id")
    private Long orderId;
	@Column(name="title")
    private String title;
	@Column(name="price")
    private BigDecimal price;
	@Column(name="num")
    private Integer num;
	@Column(name="total_fee")
    private BigDecimal totalFee;
	@Column(name="pic_path")
    private String picPath;
	@Column(name="seller_id")
    private String sellerId;

}