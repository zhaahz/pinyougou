package com.pinyougou.user.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/23 23:58
 * @Description: // TODO 登录控制器
 * @Version: 1.0
 */
@RestController
public class LoginController {

	/**
	 * @Author ZhaJing
	 * @Description //TODO 获取登录用户名
	 * @Date 8:37 2018/11/24
	 * @Param []
	 * @return
	 **/
	@GetMapping("/user/showName")
	public Map<String,Object> showName(){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();

		Map<String,Object> map = new HashMap<>();
		map.put("loginName",name);
		return map;
	}
}
