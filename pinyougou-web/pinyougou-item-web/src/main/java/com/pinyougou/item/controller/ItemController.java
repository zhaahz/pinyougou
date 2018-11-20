package com.pinyougou.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/15 10:35
 * @Description: // TODO 商品详情(返回html页面)
 * @Version: 1.0
 */
@Controller
public class ItemController {
	@Reference(timeout = 10000)
	private GoodsService goodsService;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 测试 http://item.pinyougou.com/SPU商品goodsId.html
	 * @Date 10:39 2018/11/15
	 * @Param [goodsId]: 获取请求URL中的变量
	 * @return 
	 **/
//	@GetMapping("/{goodsId}")
//	public ModelAndView getItem(@PathVariable("goodsId")Long goodsId){
//		ModelAndView mv = new ModelAndView();
//		System.out.println("goodsId:"+goodsId);
//		mv.addObject("name","zhajing");
//		mv.setViewName("item");
//		return mv;
//	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 获取商品数据
	 * @Date 10:59 2018/11/15
	 * @Param [goodsId]
	 * @return 
	 **/
	@GetMapping("/{goodsId}")
	public ModelAndView getItem(@PathVariable("goodsId")Long goodsId){
		ModelAndView mv = new ModelAndView();
		Map<String, Object> dataModel = goodsService.getGoods(goodsId);
		mv.addAllObjects(dataModel);
		mv.setViewName("item");
		return mv;
	}

	//jd的页面就是这样,商品id就是页面id.html
	//所以我要访问一个页面,12345.html;那么123456就是商品的id
	//@PathVariable 这个注解可以实现这一目的

}
