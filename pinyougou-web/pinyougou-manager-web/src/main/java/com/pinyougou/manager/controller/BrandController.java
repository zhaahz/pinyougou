package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Brand;
import com.pinyougou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.font.TrueTypeFont;

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


}
