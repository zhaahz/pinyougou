package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.ItemCat;
import com.pinyougou.service.ItemCatService;
import com.pinyougou.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/6 11:59
 * @Description: // TODO 商品分类控制器
 * @Version: 1.0
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {
	@Reference(timeout = 10000)
	private ItemCatService itemCatService;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据父id查询商品分类
	 * @Date 12:04 2018/11/6
	 * @Param [parentId]
	 * @return
	 **/
	@GetMapping("/findItemCatByParentId")
	public Map<String,Object> findItemCatByParentId(@RequestParam(value = "parentId",defaultValue = "0") Long parentId){
		Map<String,Object> map = new HashMap<>();
		List<ItemCat> itemCats = itemCatService.findItemCatByParentId(parentId);
		map.put("itemCatList",itemCats);
		return map;
	}
}
