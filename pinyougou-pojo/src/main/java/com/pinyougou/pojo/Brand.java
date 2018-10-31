package com.pinyougou.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "tb_brand")
public class Brand implements Serializable{

	    //主键id
	    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private Long id;

	    //品牌名称
	    @Column(name = "name")
		private String name;

		//品牌首字母
	    @Column(name = "first_char")
		private String firstChar;


        //10/29
	    //上面是JPA注解,是建立实体类与数据库表之间的映射关系
}
