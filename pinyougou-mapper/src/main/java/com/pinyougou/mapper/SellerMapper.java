package com.pinyougou.mapper;

import tk.mybatis.mapper.common.Mapper;

import com.pinyougou.pojo.Seller;

import java.util.List;

/**
 * SellerMapper 数据访问接口
 * @date 2018-10-29 21:26:28
 * @version 1.0
 */
public interface SellerMapper extends Mapper<Seller>{

	/** 多条件查询商家 */
	List<Seller> findAll(Seller seller);

}