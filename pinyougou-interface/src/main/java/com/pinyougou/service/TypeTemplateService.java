package com.pinyougou.service;

import com.pinyougou.pojo.TypeTemplate;
import java.util.List;
import java.io.Serializable;
/**
 * TypeTemplateService 服务接口
 * @date 2018-10-30 10:59:16
 * @version 1.0
 */
public interface TypeTemplateService {

	/** 添加方法 */
	void save(TypeTemplate typeTemplate);

	/** 修改方法 */
	void update(TypeTemplate typeTemplate);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	TypeTemplate findOne(Serializable id);

	/** 查询全部 */
	List<TypeTemplate> findAll();

	/** 多条件分页查询 */
	List<TypeTemplate> findByPage(TypeTemplate typeTemplate, int page, int rows);

}