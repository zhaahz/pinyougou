package com.pinyougou.search.listener;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.service.GoodsService;
import com.pinyougou.service.ItemSearchService;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/19 22:14
 * @Description: // TODO
 * @Version: 1.0
 */
public class DeleteMessageListener implements SessionAwareMessageListener<ObjectMessage> {

	@Reference(timeout = 30000)
	private ItemSearchService itemSearchService;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 删除商品索引消息监听器
	 * @Date 22:16 2018/11/19
	 * @Param [objectMessage, session]
	 * @return
	 **/
	@Override
	public void onMessage(ObjectMessage objectMessage, Session session) throws JMSException {

		Long[] ids = (Long[]) objectMessage.getObject();

		itemSearchService.delete(ids);
	}
}
