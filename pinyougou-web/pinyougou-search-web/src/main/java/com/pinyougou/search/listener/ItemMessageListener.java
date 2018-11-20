package com.pinyougou.search.listener;
import java.math.BigDecimal;
import java.util.*;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.pinyougou.pojo.Item;
import com.pinyougou.service.GoodsService;
import com.pinyougou.service.ItemSearchService;
import com.pinyougou.solr.SolrItem;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/19 21:37
 * @Description: // TODO 商品消息监听器(同步索引到索引库)
 * @Version: 1.0
 */
public class ItemMessageListener implements SessionAwareMessageListener<ObjectMessage>{
	@Reference(timeout = 30000)
	private GoodsService goodsService;
	@Reference(timeout = 30000)
	private ItemSearchService itemSearchService;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 同步索引
	 * @Date 21:40 2018/11/19
	 * @Param [objectMessage, session]
	 * @return
	 **/
	@Override
	public void onMessage(ObjectMessage objectMessage, Session session) throws JMSException {
		System.out.println("===ItemMessageListener===");

		//获取消息内容 得到上架的id
		Long[] ids = (Long[]) objectMessage.getObject();

		//查询上架的SKU商品数据
		List<Item> itemList = goodsService.findItemByGoodsId(ids);

		//判断集合并遍历,放进索引库
		if (itemList.size()>0){
			ArrayList<SolrItem> solrItems = new ArrayList<>();
			for (Item item : itemList) {
				SolrItem solrItem = new SolrItem();
				solrItem.setSpecMap(JSON.parseObject(item.getSpec(),Map.class));
				solrItem.setId(item.getId());
				solrItem.setGoodsId(item.getGoodsId());
				solrItem.setTitle(item.getTitle());
				solrItem.setPrice(item.getPrice());
				solrItem.setImage(item.getImage());
				solrItem.setBrand(item.getBrand());
				solrItem.setCategory(item.getCategory());
				solrItem.setSeller(item.getSeller());
				solrItem.setUpdateTime(item.getUpdateTime());
				solrItems.add(solrItem);
			}
			//把SKU商品数据同步到索引库
			itemSearchService.saveOrUpdate(solrItems);


		}

	}
}
