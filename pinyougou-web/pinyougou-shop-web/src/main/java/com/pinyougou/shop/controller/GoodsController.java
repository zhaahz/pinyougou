package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.Goods;
import com.pinyougou.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/6 08:38
 * @Description: // TODO 商品管理类
 * @Version: 1.0
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
	@Reference(timeout = 10000)
	private GoodsService goodsService;

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Destination solrQueue;
	//destination: 主题名称
	@Autowired
	private Destination solrDeleteQueue;

	@Autowired
	private Destination pageTopic;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 分页条件查询商品
	 * @Date 17:18 2018/11/17
	 * @Param [goods, page, rows]
	 * @return
	 **/
	@GetMapping("/findByPage")
	public PageResult findByPage( Goods goods,Integer page,Integer rows){
		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
		goods.setSellerId(sellerId);
		if(StringUtils.isNoneBlank(goods.getGoodsName())){
			try {
				goods.setGoodsName(new String(goods.getGoodsName().getBytes("ISO8859-1"),"UTF-8"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return goodsService.findByPage(goods,page,rows);
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 添加商品[SPU,SKU,商品描述]
	 * @Date 9:45 2018/11/6
	 * @Param [goods]
	 * @return
	 **/
	@PostMapping("/save")
	public boolean save(@RequestBody Goods goods){
		try {
			/**商家后台,所以要获取当前商家的id*/
			String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
			goods.setSellerId(sellerId);
			goodsService.save(goods);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 批量删除
	 * @Date 11:30 2018/11/18
	 * @Param []
	 * @return
	 **/
	@GetMapping("/updateIsDelete")
	public boolean updateIsDelete(String isDelete,Long[] ids){

		try {
			goodsService.updateIsDelete(ids,isDelete);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 商品的上架与下架
	 * @Date 16:55 2018/11/18
	 * @Param []
	 * @return
	 **/
//	@PostMapping("/updateIsMarketable")
//	public boolean updateIsMarketable(String isMarketable,@RequestBody Long[] ids){
//		try {
//			goodsService.updateIsMarketable(isMarketable,ids);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 商品的上架与下架(修改销售状态)
	 * @Date 21:24 2018/11/19
	 * @Param [isMarketable, ids]
	 * @return 
	 **/
	@PostMapping("/updateIsMarketable")
	public boolean updateIsMarketable(String isMarketable,@RequestBody Long[] ids){
		try {
			goodsService.updateIsMarketable(isMarketable,ids);
			//判断商品的上下架状态
			if("1".equals(isMarketable)){
				//发送消息,生产商品索引
				jmsTemplate.send(solrQueue, new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						return session.createObjectMessage(ids);
					}
				});
				//发送消息,上架生成页面
				//不直接发送ids,循环遍历ids,循环发送
				for (Long goodsId : ids) {
					jmsTemplate.send(pageTopic, new MessageCreator() {
						@Override
						public Message createMessage(Session session) throws JMSException {
							return session.createTextMessage(goodsId.toString());
						}
					});
				}

			}else {
				//表示商品下架
				jmsTemplate.send(solrDeleteQueue, new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						return session.createObjectMessage(ids);
					}
				});


			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
