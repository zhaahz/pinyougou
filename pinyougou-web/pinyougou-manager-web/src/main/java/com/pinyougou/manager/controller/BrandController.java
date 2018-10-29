package com.pinyougou.manager.controller;

import com.pinyougou.pojo.Brand;
import com.pinyougou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BrandController {

	@Autowired
	private BrandService brandService;

	@RequestMapping("brand/findAll")
	public List<Brand> findAll(){
		return brandService.findAll();
	}


}
