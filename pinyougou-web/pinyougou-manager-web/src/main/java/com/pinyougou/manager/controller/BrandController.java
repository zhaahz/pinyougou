package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.Brand;
import com.pinyougou.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

	@Reference(timeout = 10000)
	private BrandService brandService;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 
	 * @Date 15:08 2018/10/31
	 * @Param []
	 * @return 
	 **/
	@GetMapping("/findAll")
	public List<Brand> findAll(){

		return brandService.findAll();
	}

	@PostMapping("/save")
	//忘了这个requestBoday的作用了
	public boolean save(@RequestBody Brand brand){

		try {
			brandService.save(brand);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}


		return false;
	}
	//@RequestBody能接受json格式的字符串
	//当前端传过来的值是json格式的字符串 如 {name:"zhajing",age:18},只能用@RequestBody接收

	/**
	 * @Author ZhaJing
	 * @Description //修改品牌
	 * @Date 21:22 2018/10/31
	 * @Param [Brand]
	 * @return
	 **/
	@PostMapping("/update")
	public boolean update(@RequestBody Brand brand ){
		try {
			brandService.update(brand);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * @Author ZhaJing
	 * @Description //分页条件查询
	 * @Date 9:29 2018/11/1
	 * @Param [page 当前页, rows 每页大小]
	 * @return 封装结果实体类
	 **/
//	@GetMapping("/findByPage")
//	public PageResult findByPage(Brand brand,Integer page,Integer rows){
//
//		/** GET 请求中文转码 */
//		if (brand != null && StringUtils.isNoneBlank(brand.getName())) {
//			try {
//				brand.setName(new String(brand.getName()
//						.getBytes("ISO8859-1"), "UTF-8"));
//			}catch (Exception ex){
//				ex.printStackTrace();
//			}
//		}
//		//为什么Get请求要进行中文转码
//
//		return brandService.findByPage(brand, page, rows);
//	}

	@GetMapping("/findByPage")
	public PageInfo<Brand> findByPage(Brand brand,Integer page,Integer rows){

		/** GET 请求中文转码 */
		if (brand != null && StringUtils.isNoneBlank(brand.getName())) {
			try {
				brand.setName(new String(brand.getName()
						.getBytes("ISO8859-1"), "UTF-8"));
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
		//为什么Get请求要进行中文转码

		return brandService.findByPage(brand, page, rows);
	}


	/**
	 * @Author ZhaJing
	 * @Description //删除品牌
	 * @Date 10:44 2018/11/1
	 * @Param [ids ids字符串]
	 * @return
	 **/
	@GetMapping("/delete")
	public boolean remove(Long[] ids){

//		String[] strings = ids.split(",");
//
//
//		for (String string : strings) {
//			int id = Integer.parseInt(string);
//			brandService.delete(id);
//		}
//		return true;

		/**以前都是传入字符串数组,然后将字符串数组进行切割,切割成id,然后查询*/
		/**现在dao层有方法,可根据条件Example删除数据*/

		try {
			brandService.deleteAll(ids);
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

}
