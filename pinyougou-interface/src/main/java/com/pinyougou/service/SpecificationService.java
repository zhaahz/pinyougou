package com.pinyougou.service;

import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.Specification;
import com.pinyougou.pojo.SpecificationOption;

import java.util.List;
import java.io.Serializable;
import java.util.Map;

/**
 * SpecificationService 服务接口
 * @date 2018-10-30 10:59:16
 * @version 1.0
 */
public interface SpecificationService {

	/** 添加方法 */
	void save(Specification specification);

	/** 修改方法 */
	void update(Specification specification);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	Specification findOne(Serializable id);

	/** 查询全部 */
	List<Specification> findAll();

	/** 多条件分页查询 */
	//List<Specification> findByPage(Specification specification, int page, int rows);
	PageInfo<Specification> findByPage(Specification specification, int page, int rows);

	
	/**
	 * @Author ZhaJing
	 * @Description //TODO  查询全部规格(id与specName)
	 * @Date 10:10 2018/11/8
	 * @Param []
	 * @return 
	 **/
	List<Map<String, Object>> findAllByIdAndName();

	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据规格id查询规格选项
	 * @Date 20:42 2018/11/7
	 * @Param
	 * @return
	 **/
	List<SpecificationOption> findSpecOption(Long id);
}