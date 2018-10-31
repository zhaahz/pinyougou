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
@Table(name="tb_order")
@Data
public class Order implements Serializable{
   
	private static final long serialVersionUID = 1240990444368827377L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id")
	private Long orderId;
	@Column(name="payment")
    private BigDecimal payment;
	@Column(name="payment_type")
    private String paymentType;
	@Column(name="post_fee")
    private String postFee;
	@Column(name="status")
    private String status;
	@Column(name="create_time")
    private Date createTime;
	@Column(name="update_time")
    private Date updateTime;
	@Column(name="payment_time")
    private Date paymentTime;
	@Column(name="consign_time")
    private Date consignTime;
	@Column(name="end_time")
    private Date endTime;
	@Column(name="close_time")
    private Date closeTime;
	@Column(name="shipping_name")
    private String shippingName;
	@Column(name="shipping_code")
    private String shippingCode;
	@Column(name="user_id")
    private String userId;
	@Column(name="buyer_message")
    private String buyerMessage;
	@Column(name="buyer_nick")
    private String buyerNick;
	@Column(name="buyer_rate")
    private String buyerRate;
	@Column(name="receiver_area_name")
    private String receiverAreaName;
	@Column(name="receiver_mobile")
    private String receiverMobile;
	@Column(name="receiver_zip_code")
    private String receiverZipCode;
	@Column(name="receiver")
    private String receiver;
	@Column(name="expire")
    private Date expire;
	@Column(name="invoice_type")
    private String invoiceType;
	@Column(name="source_type")
    private String sourceType;
	@Column(name="seller_id")
    private String sellerId;
	
  }