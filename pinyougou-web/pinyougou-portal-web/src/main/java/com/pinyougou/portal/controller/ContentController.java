package com.pinyougou.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Content;
import com.pinyougou.service.ContentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/9 15:06
 * @Description: // TODO 广告内容控制器
 * @Version: 1.0
 */
@RestController
@RequestMapping("/content")
public class ContentController {
	@Reference(timeout = 10000)
	private ContentService contentService;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据categoryId查询content
	 * @Date 15:10 2018/11/9
	 * @Param [id]
	 * @return
	 **/
	@GetMapping("/findContentByCategoryId")
	public List<Content> findContentByCategoryId(Long categoryId){
		return contentService.findContentByCategoryId(categoryId);
	}
}
