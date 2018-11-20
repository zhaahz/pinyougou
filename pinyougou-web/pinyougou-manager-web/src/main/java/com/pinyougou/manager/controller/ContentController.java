package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.Content;
import com.pinyougou.service.ContentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//怎么导的包不一样?

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/8 23:34
 * @Description: // TODO contentController控制器类
 * @Version: 1.0
 */
@RestController
@RequestMapping("/content")
public class ContentController {

	@Reference(timeout = 10000)
	private ContentService contentService;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 查询全部方法
	 * @Date 11:19 2018/11/9
	 * @Param []
	 * @return 
	 **/
	@GetMapping("/findAll")
	public List<Content> findAll() {
		return contentService.findAll();
	}


	/**
	 * @Author ZhaJing
	 * @Description //TODO 多条件分页查询方法
	 * @Date 11:19 2018/11/9
	 * @Param [content, page, rows]
	 * @return 
	 **/
	@GetMapping("/findByPage")
	public PageResult findByPage(Content content,
								 Integer page, Integer rows) {
		try {
			return contentService.findByPage(content, page, rows);
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
	public Content findOne(Long id) {
		try {
			return contentService.findOne(id);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}


	/**
	 * @Author ZhaJing
	 * @Description //TODO 添加方法
	 * @Date 11:19 2018/11/9
	 * @Param [content]
	 * @return 
	 **/
	@PostMapping("/save")
	public boolean save(@RequestBody Content content) {
		try {
			contentService.save(content);
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
	 * @Param [content]
	 * @return 
	 **/
	@PostMapping("/update")
	public boolean update(@RequestBody Content content) {
		try {
			contentService.update(content);
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
			contentService.deleteAll(ids);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}

