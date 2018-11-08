package com.pinyougou.service;

import com.pinyougou.pojo.ItemCat;
import java.util.List;
import java.io.Serializable;
/**
 * ItemCatService 服务接口
 * @date 2018-10-30 10:59:15
 * @version 1.0
 */
public interface ItemCatService {
	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据parentId查询
	 * @Date 11:32 2018/11/8
	 * @Param [parentId]
	 * @return
	 **/
	List<ItemCat> findItemCatByParentId(Long parentId);

	/** 添加方法 */
	void save(ItemCat itemCat);

	/** 修改方法 */
	void update(ItemCat itemCat);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	ItemCat findOne(Serializable id);

	/** 查询全部 */
	List<ItemCat> findAll();

	/** 多条件分页查询 */
	List<ItemCat> findByPage(ItemCat itemCat, int page, int rows);

}