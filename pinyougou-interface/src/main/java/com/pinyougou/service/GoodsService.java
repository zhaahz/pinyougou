package com.pinyougou.service;

import com.github.pagehelper.PageInfo;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.Goods;
import com.pinyougou.pojo.Item;

import java.util.List;
import java.io.Serializable;
import java.util.Map;

/**
 * GoodsService 服务接口
 * @date 2018-10-30 10:59:15
 * @version 1.0
 */
public interface GoodsService {

	/** 添加方法 */
	void save(Goods goods);

	/** 修改方法 */
	void update(Goods goods);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	Goods findOne(Serializable id);

	/** 查询全部 */
	List<Goods> findAll();

	/** 多条件分页查询 */

	PageResult findByPage(Goods goods, int page, int rows);

	/**
	 * @Author ZhaJing
	 * @Description //TODO 商品审核
	 * @Date 9:19 2018/11/9
	 * @Param [ids, status]
	 * @return
	 **/
	void updateStatus(Long[] ids, String status);

	/**
	 * @Author ZhaJing
	 * @Description //TODO 获取商品信息
	 * @Date 11:26 2018/11/15
	 * @Param [goodsId]
	 * @return 
	 **/
	Map<String,Object> getGoods(Long goodsId);

	/**
	 * @Author ZhaJing
	 * @Description //TODO 商品逻辑删除
	 * @Date 9:05 2018/11/17
	 * @Param
	 * @return
	 **/
	public void updateIsDelete(Long[] ids,
							   String isDelete);

	/**
	 * @Author ZhaJing
	 * @Description //TODO 商品的上架与下架
	 * @Date 17:05 2018/11/18
	 * @Param [isMarketable, ids]
	 * @return 
	 **/
	void updateIsMarketable(String isMarketable, Long[] ids);

	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据商品id查询SKU
	 * @Date 21:45 2018/11/19
	 * @Param [ids]
	 * @return 
	 **/
	List<Item> findItemByGoodsId(Long[] ids);
}