package com.pinyougou.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * 规格实体类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2017年12月7日 下午3:31:00
 * @version 1.0
 */
@Table(name="tb_specification")
@Data
public class Specification implements Serializable{
   
	private static final long serialVersionUID = -972374525762485421L;
	/** 主键  规格编号 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	/** 规格名称 */
	@Column(name="spec_name")
    private String specName;
    /** 规格属性集合 */
	@Transient
    private List<SpecificationOption> specificationOptions; 

	//@Transient 告诉通用mapper该属性不是表中的列名
}