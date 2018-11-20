package com.pinyougou.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.service.ItemSearchService;
import com.pinyougou.solr.SolrItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.ScoredPage;


import java.util.*;

/**
 * 商品搜索服务接口实现类
 *
 * @author lee.siu.wah
 * @version 1.0
 * <p>File Created at 2018-11-12<p>
 */
@Service(interfaceName = "com.pinyougou.service.ItemSearchService")
public class ItemSearchServiceImpl implements ItemSearchService {

    @Autowired
    private SolrTemplate solrTemplate;

	/** 搜索方法 */
	@Override
	public Map<String, Object> search(Map<String,Object> params) {
		/** 创建Map集合封装返回数据 **/
		Map<String, Object> data = new HashMap<>();
		/** 创建查询对象 */
		Query query = new SimpleQuery("*:*");
		/** 获取检索关键字 */
		String keywords = (String)params.get("keywords");
		/** 判断检索关键字 */
		if (StringUtils.isNoneBlank(keywords)){
			/** 创建查询条件 */
			Criteria criteria = new Criteria("keywords").is(keywords);
			/** 添加查询条件 */
			query.addCriteria(criteria);
		}
		/** 分页检索 */
		ScoredPage<SolrItem> scoredPage = solrTemplate
				.queryForPage(query, SolrItem.class);
		/** 获取内容 */
		data.put("rows", scoredPage.getContent());
		return data;
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 添加或修改商品索引
	 * @Date 21:56 2018/11/19
	 * @Param [solrItems]
	 * @return
	 **/
	@Override
	public void saveOrUpdate(ArrayList<SolrItem> solrItems) {
		UpdateResponse updateResponse = solrTemplate.saveBeans(solrItems);
		if (updateResponse.getStatus()==0) {
			solrTemplate.commit();
		}else {
			solrTemplate.rollback();
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 删除商品索引
	 * @Date 22:18 2018/11/19
	 * @Param [ids]
	 * @return
	 **/
	@Override
	public void delete(Long[] ids) {
		Query query = new SimpleQuery();
		Criteria criteria = new Criteria("goodsId").in(Arrays.asList(ids));
		query.addCriteria(criteria);
		UpdateResponse delete = solrTemplate.delete(query);
		if (delete.getStatus()==0){
			solrTemplate.commit();
		}else {
			solrTemplate.rollback();
		}

	}
}