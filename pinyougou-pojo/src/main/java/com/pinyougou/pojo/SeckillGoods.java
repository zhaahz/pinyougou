package com.pinyougou.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @Author ZhaJing
 * @Description //品牌实体类
 * @Date 15:08 2018/10/31
 * @version 1.0
 *
 **/
@Table(name="tb_seckill_goods")
@Data
public class SeckillGoods implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="goods_id")
    private Long goodsId;
    @Column(name="item_id")
    private Long itemId;
    @Column(name="title")
    private String title;
    @Column(name="small_pic")
    private String smallPic;
    @Column(name="price")
    private BigDecimal price;
    @Column(name="cost_price")
    private BigDecimal costPrice;
    @Column(name="seller_id")
    private String sellerId;
    @Column(name="create_time")
    private Date createTime;
    @Column(name="check_time")
    private Date checkTime;
    @Column(name="status")
    private String status;
    @Column(name="start_time")
    private Date startTime;
    @Column(name="end_time")
    private Date endTime;
    @Column(name="num")
    private Integer num;
    @Column(name="stock_count")
    private Integer stockCount;
    @Column(name="introduction")
    private String introduction;

}