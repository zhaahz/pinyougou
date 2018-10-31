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
@Table(name="tb_seckill_order")
@Data
public class SeckillOrder implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="seckill_id")
    private Long seckillId;
    @Column(name="money")
    private BigDecimal money;
    @Column(name="user_id")
    private String userId;
    @Column(name="seller_id")
    private String sellerId;
    @Column(name="create_time")
    private Date createTime;
    @Column(name="pay_time")
    private Date payTime;
    @Column(name="status")
    private String status;
    @Column(name="receiver_address")
    private String receiverAddress;
    @Column(name="receiver_mobile")
    private String receiverMobile;
    @Column(name="receiver")
    private String receiver;
    @Column(name="transaction_id")
    private String transactionId;

}