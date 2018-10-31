package com.pinyougou.service;

import com.pinyougou.pojo.Brand;

import java.io.Serializable;
import java.util.List;

/**
 * BrandService 服务接口
 * @date 2018-10-30 10:59:15
 * @version 1.0
 */
public interface BrandService {

	/** 查询全部 */
	List<Brand> findAll();

	//10/29
	//本来想在这里重新定义一个方法来进行分页操作的,但是interface层
	//没有依赖mapper层,所以没有pagehelper的插件,所以只能在impl层改造findAll方法

	/** 添加方法 */
	void save(Brand brand);

	/** 修改方法 */
	void update(Brand brand);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	Brand findOne(Serializable id);


	/** 多条件分页查询 */
	List<Brand> findByPage(Brand brand, int page, int rows);


}
