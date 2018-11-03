package com.pinyougou.mapper;

import com.pinyougou.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


import java.util.List;

/**
 * @Author ZhaJing
 * @Description //品牌管理
 * @Date 8:50 2018/11/1
 * @version 1.0
 *
 **/
public interface BrandMapper extends Mapper<Brand> {

//	@Select("select * from tb_brand order by id ASC ")
//	List<Brand> findAll();

	//10/29
	//感觉就是spring-data-jpa;像是继承了一个存储-
	//简易增删改查方法的类



	/**
	 * @Author ZhaJing
	 * @Description //条件查询品牌
	 * @Date 8:50 2018/11/1
	 * @Param [brand]
	 * @return
	 **/
	List<Brand> findTiaoJian(Brand brand);
}
