package com.pinyougou.manager.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.ContentCategory;
import com.pinyougou.service.ContentCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/8 23:33
 * @Description: // TODO contentCategoryController 控制器类
 * @Version: 1.0
 */
@RestController
@RequestMapping("/contentCategory")
public class ContentCategoryController {

	@Reference(timeout = 10000)
	private ContentCategoryService contentCategoryService;


	/**
	 * @Author ZhaJing
	 * @Description //TODO 查询全部方法
	 * @Date 11:18 2018/11/9
	 * @Param []
	 * @return 
	 **/
	@GetMapping("/findAll")
	public List<ContentCategory> findAll() {
		return contentCategoryService.findAll();
	}


	/**
	 * @Author ZhaJing
	 * @Description //TODO 多条件分页查询方法
	 * @Date 11:19 2018/11/9
	 * @Param [contentCategory, page, rows]
	 * @return 
	 **/
	@GetMapping("/findByPage")
	public PageResult findByPage(ContentCategory contentCategory,
								 Integer page, Integer rows) {
		try {
			return contentCategoryService.findByPage(contentCategory, page, rows);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}


	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据主键id查询方法
	 * @Date 11:19 2018/11/9
	 * @Param [id]
	 * @return 
	 **/
	@GetMapping("/findOne")
	public ContentCategory findOne(Long id) {
		try {
			return contentCategoryService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}


	/**
	 * @Author ZhaJing
	 * @Description //TODO 添加方法
	 * @Date 11:19 2018/11/9
	 * @Param [contentCategory]
	 * @return 
	 **/
	@PostMapping("/save")
	public boolean save(@RequestBody ContentCategory contentCategory) {
		try {
			contentCategoryService.save(contentCategory);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}


	/**
	 * @Author ZhaJing
	 * @Description //TODO 修改方法
	 * @Date 11:19 2018/11/9
	 * @Param [contentCategory]
	 * @return 
	 **/
	@PostMapping("/update")
	public boolean update(@RequestBody ContentCategory contentCategory) {
		try {
			contentCategoryService.update(contentCategory);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}


	/**
	 * @Author ZhaJing
	 * @Description //TODO 删除方法
	 * @Date 11:20 2018/11/9
	 * @Param [ids]
	 * @return 
	 **/
	@GetMapping("/delete")
	public boolean delete(Long[] ids) {
		try {
			contentCategoryService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}

