package com.pinyougou.service;

import com.pinyougou.solr.SolrItem;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/13 12:26
 * @Description: // TODO
 * @Version: 1.0
 */
public interface ItemSearchService {

	/**
	 * @Author ZhaJing
	 * @Description //TODO 商品搜索
	 * @Date 12:27 2018/11/13
	 * @Param
	 * @return
	 **/
	Map<String,Object> search(Map<String,Object> params);

	/**
	 * @Author ZhaJing
	 * @Description //TODO 添加或修改商品索引
	 * @Date 21:56 2018/11/19
	 * @Param [solrItems]
	 * @return 
	 **/
	void saveOrUpdate(ArrayList<SolrItem> solrItems);

	/**
	 * @Author ZhaJing
	 * @Description //TODO 删除商品索引
	 * @Date 22:17 2018/11/19
	 * @Param [ids]
	 * @return 
	 **/
	void delete(Long[] ids);
}
