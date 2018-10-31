package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Brand;
import com.pinyougou.service.BrandService;
import org.springframework.web.bind.annotation.*;
import sun.font.TrueTypeFont;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

	@Reference(timeout = 10000)
	private BrandService brandService;

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


}
