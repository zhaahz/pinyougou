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
@Table(name="tb_freight_template")
@Data
public class FreightTemplate implements Serializable{
    
	private static final long serialVersionUID = -6912650278645533046L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="seller_id")
    private String sellerId;
	@Column(name="is_default")
    private String isDefault;
	@Column(name="name")
    private String name;
	@Column(name="send_time_type")
    private String sendTimeType;
	@Column(name="price")
    private Long price;
	@Column(name="create_time")
    private Date createTime;

}