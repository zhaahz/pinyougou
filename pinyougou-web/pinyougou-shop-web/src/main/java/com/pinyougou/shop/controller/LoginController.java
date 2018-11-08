package com.pinyougou.shop.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/6 19:12
 * @Description: // TODO 商家登录
 * @Version: 1.0
 */
@RestController
public class LoginController {
	/** 显示登录用户名 */
	@GetMapping("/showLoginName")
	public Map<String, String> showLoginName(){
		// 获取登录用户名
		String loginName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		Map<String,String> data = new HashMap<>();
		data.put("loginName", loginName);
		return data;
	}
}
