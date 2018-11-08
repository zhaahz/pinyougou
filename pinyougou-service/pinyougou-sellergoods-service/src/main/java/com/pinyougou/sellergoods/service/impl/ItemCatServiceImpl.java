package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.mapper.ItemCatMapper;
import com.pinyougou.pojo.ItemCat;
import com.pinyougou.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/6 12:55
 * @Description: // TODO 商品分类服务接口实现类
 * @Version: 1.0
 */
@Service(interfaceName = "com.pinyougou.service.ItemCatService")
@Transactional
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private ItemCatMapper itemCatMapper;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据parentId查询模板分类数据
	 * @Date 15:00 2018/11/6
	 * @Param [parentId]
	 * @return
	 **/
	@Override
	public List<ItemCat> findItemCatByParentId(Long parentId) {
		try {
			ItemCat itemCat = new ItemCat();
			itemCat.setParentId(parentId);

			return itemCatMapper.select(itemCat);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * @Author ZhaJing
	 * @Description //TODO 添加商品分类
	 * @Date 15:05 2018/11/8
	 * @Param [itemCat]
	 * @return
	 **/
	@Override
	public void save(ItemCat itemCat) {
		try {
			itemCatMapper.insertSelective(itemCat);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 修改商品分类
	 * @Date 15:13 2018/11/8
	 * @Param [itemCat]
	 * @return
	 **/
	@Override
	public void update(ItemCat itemCat) {
		try {
			itemCatMapper.updateByPrimaryKey(itemCat);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void delete(Serializable id) {

	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 批量删除
	 * @Date 16:00 2018/11/8
	 * @Param [ids]
	 * @return
	 **/
	@Override
	public void deleteAll(Serializable[] ids) {
		Example example = new Example(ItemCat.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andIn("id", Arrays.asList(ids));
		itemCatMapper.deleteByExample(example);
	}

	@Override
	public ItemCat findOne(Serializable id) {
		return null;
	}

	@Override
	public List<ItemCat> findAll() {
		return null;
	}

	@Override
	public List<ItemCat> findByPage(ItemCat itemCat, int page, int rows) {
		return null;
	}


}
