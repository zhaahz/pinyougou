package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TypeTemplate;
import com.pinyougou.service.TypeTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/6 12:35
 * @Description: // TODO 类型模板控制器
 * @Version: 1.0
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {
	@Reference(timeout = 10000)
	private TypeTemplateService typeTemplateService;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 查找品牌
	 * @Date 20:18 2018/11/13
	 * @Param [id]
	 * @return 
	 **/
	@GetMapping("/findOne")
	public TypeTemplate findOne(Long id){
		return typeTemplateService.findOne(id);
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据模板id查询所有规格和规格选项
	 * @Date 9:45 2018/11/7
	 * @Param [id]
	 * @return 
	 **/
	@GetMapping("/findSpecByTemplateId")
	public List<Map> findSpecByTemplateId(Long id){
		return typeTemplateService.findSpecByTemplateId(id);
	}
}
