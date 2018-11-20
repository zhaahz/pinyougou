package com.pinyougou.item.listener;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.service.GoodsService;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Session;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/20 19:16
 * @Description: // TODO 消息监听器
 * @Version: 1.0
 */
public class PageMessageListener implements SessionAwareMessageListener<TextMessage>{

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Reference(timeout = 10000)
	private GoodsService goodsService;
	@Value("${pageDir}")
	private String pageDir;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 商品上架生成页面
	 * @Date 19:17 2018/11/20
	 * @Param [objectMessage, session]
	 * @return
	 **/
	@Override
	public void onMessage(TextMessage textMessage, Session session) throws JMSException {
		try {
			String goodsId = textMessage.getText();
			System.out.println("goodsId: " + goodsId);

			//根据模板文件获取模板对象(freemarker)
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate("item.ftl");

			//获取数据模型
			Map<String, Object> data = goodsService.getGoods(Long.valueOf(goodsId));
			//创建输出流(在这单独写,是为了关闭)
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(pageDir+goodsId+".html"),"UTF-8");
			//填充模板生成的静态html页面
			template.process(data,writer);
			//关闭输出流
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}


	}
}
