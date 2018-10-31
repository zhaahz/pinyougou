package com.pinyougou.mapper;

import com.pinyougou.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


import java.util.List;


public interface BrandMapper extends Mapper<Brand> {

//	@Select("select * from tb_brand order by id ASC ")
//	List<Brand> findAll();

	//10/29
	//感觉就是spring-data-jpa;像是继承了一个存储-
	//简易增删改查方法的类
}
