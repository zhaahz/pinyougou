package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Goods;
import com.pinyougou.service.GoodsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
