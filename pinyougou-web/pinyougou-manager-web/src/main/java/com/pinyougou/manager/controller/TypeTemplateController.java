package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.TypeTemplate;
import com.pinyougou.service.TypeTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/7 21:09
 * @Description: // TODO 模板管理类
 * @Version: 1.0
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {
	@Reference(timeout = 10000)
	private TypeTemplateService typeTemplateService;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 分页条件查询模板
	 * @Date 21:10 2018/11/7
	 * @Param []
	 * @return
	 **/
	@GetMapping("/findByPage")
	public PageInfo<TypeTemplate> findByPage(Integer page,Integer rows,TypeTemplate typeTemplate){
		if(typeTemplate != null && StringUtils.isNoneBlank(typeTemplate.getName())){
			try {
				typeTemplate.setName(new String(typeTemplate.getName()
				.getBytes("ISO8859-1"),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return typeTemplateService.findByPage(typeTemplate,page,rows);
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 添加模板
	 * @Date 10:28 2018/11/8
	 * @Param [typeTemplate]
	 * @return
	 **/
	@PostMapping("/save")
	public boolean save(@RequestBody TypeTemplate typeTemplate){
		try {
			typeTemplateService.save(typeTemplate);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @Author ZhaJing
	 * @Description //TODO 修改模板数据
	 * @Date 10:41 2018/11/8
	 * @Param [typeTemplate]
	 * @return 
	 **/
	@PostMapping("/update")
	public boolean update(@RequestBody TypeTemplate typeTemplate){
		try {
			typeTemplateService.update(typeTemplate);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 删除模板
	 * @Date 11:23 2018/11/8
	 * @Param [ids]
	 * @return 
	 **/
	@GetMapping("/delete")
	public boolean deltet(Long[] ids){
		try {
			typeTemplateService.deleteAll(ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 查询id和name(商品分类)
	 * @Date 15:12 2018/11/8
	 * @Param []
	 * @return
	 **/
	@GetMapping("/findIdAndName")
	public List<TypeTemplate> findIdAndName(){
		return typeTemplateService.findIdAndName();
	}


}
