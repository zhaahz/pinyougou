package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.Specification;
import com.pinyougou.pojo.SpecificationOption;
import com.pinyougou.service.SpecificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/7 16:27
 * @Description: // TODO 规格管理类
 * @Version: 1.0
 */
@RestController
@RequestMapping("/specification")
public class SpecificationController {
	@Reference(timeout = 10000)
	private SpecificationService specificationService;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 分页条件查询规格
	 * @Date 16:38 2018/11/7
	 * @Param [page, rows, specification]
	 * @return
	 **/
	@GetMapping("/findByPage")
	public PageInfo<Specification>findByPage(Integer page, Integer rows, Specification specification){

		if(specification != null && StringUtils.isNoneBlank(specification.getSpecName())){
			try {
				specification.setSpecName(
						new String(specification.getSpecName().getBytes("ISO8859-1"),"UTF-8")
				);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return specificationService.findByPage(specification, page, rows);
	}
	
	/**
	 * @Author ZhaJing
	 * @Description //TODO 批量删除规格
	 * @Date 18:37 2018/11/7
	 * @Param [ids]
	 * @return 
	 **/
	@GetMapping("/delete")
	public boolean delete(Long[] ids){
		try {
			specificationService.deleteAll(ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @Author ZhaJing
	 * @Description //TODO 添加规格
	 * @Date 20:07 2018/11/7
	 * @Param [specification]
	 * @return 
	 **/
	@PostMapping("/save")
	public boolean save(@RequestBody Specification specification){
		try {
			specificationService.save(specification);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据规格id查询规格选项
	 * @Date 20:48 2018/11/7
	 * @Param [id]
	 * @return
	 **/
	@GetMapping("/findSpecOption")
	public List<SpecificationOption> findSpecOption(Long id){
		return specificationService.findSpecOption(id);
	}

	@PostMapping("/update")
	public boolean update(@RequestBody Specification specification){
		try {

			specificationService.update(specification);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 查询id和name(下拉列表)
	 * @Date 9:54 2018/11/8
	 * @Param []
	 * @return
	 **/
	@GetMapping("/findSpecList")
	public List<Map<String,Object>> findSpecList(){
		return specificationService.findAllByIdAndName();
	}
}
