package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Seller;
import com.pinyougou.service.SellerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/5 10:46
 * @Description: // TODO 商家管理
 * @Version: 1.0
 */
@RestController
@RequestMapping("/seller")
public class SellerController {
	@Reference(timeout = 10000)
	private SellerService sellerService;


	/**
	 * @Author ZhaJing
	 * @Description //TODO 商家申请
	 * @Date 15:41 2018/11/5
	 * @Param [seller]
	 * @return 
	 **/
	@PostMapping("/save")
	public boolean save(@RequestBody Seller seller){
		try {
			/** 密码加密 */
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String password = passwordEncoder.encode(seller.getPassword());
			seller.setPassword(password);
			sellerService.save(seller);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
