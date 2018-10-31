package com.pinyougou.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
/**
 * @Author ZhaJing
 * @Description //品牌实体类
 * @Date 15:08 2018/10/31
 * @version 1.0
 *
 **/
@Table(name="tb_pay_log")
@Data
public class PayLog implements Serializable{
   
	private static final long serialVersionUID = 362140345448460733L;
	@Id @Column(name="out_trade_no")
	private String outTradeNo;
	@Column(name="create_time")
    private Date createTime;
	@Column(name="pay_time")
    private Date payTime;
	@Column(name="total_fee")
    private Long totalFee;
	@Column(name="user_id")
    private String userId;
	@Column(name="transaction_id")
    private String transactionId;
	@Column(name="trade_state")
    private String tradeState;
	@Column(name="order_list")
    private String orderList;
	@Column(name="pay_type")
    private String payType;

    }

