package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.ItemCat;
import com.pinyougou.service.ItemCatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/8 11:28
 * @Description: // TODO 商品分类
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
	 * @Date 11:31 2018/11/8
	 * @Param [parentId]
	 * @return
	 **/
	@GetMapping("/findItemCatByParentId")
	public List<ItemCat> findItemCatByParentId(Long parentId){
		return itemCatService.findItemCatByParentId(parentId);
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 添加商品分类
	 * @Date 15:04 2018/11/8
	 * @Param [itemCat]
	 * @return
	 **/
	@PostMapping("/save")
	public boolean save(@RequestBody ItemCat itemCat){
		try {
			itemCatService.save(itemCat);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 修改商品分类
	 * @Date 15:12 2018/11/8
	 * @Param []
	 * @return
	 **/
	@PostMapping("/update")
	public boolean update(@RequestBody ItemCat itemCat){
		try {
			itemCatService.update(itemCat);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 批量删除
	 * @Date 16:00 2018/11/8
	 * @Param [ids]
	 * @return
	 **/
	@GetMapping("/delete")
	public boolean delete(Long[] ids){
		try {
			itemCatService.deleteAll(ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
