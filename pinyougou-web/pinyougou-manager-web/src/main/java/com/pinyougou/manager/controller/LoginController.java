package com.pinyougou.manager.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/5 09:47
 * @Description: // TODO 获取登录用户名控制器
 * @Version: 1.0
 */
@RestController
public class LoginController {

	/**
	 * @Author ZhaJing
	 * @Description //TODO 显示登录用户名
	 * @Date 16:52 2018/11/5
	 * @Param []
	 * @return 
	 **/
	@GetMapping("/showLoginName")
	public Map<String,Object> showLoginName(){
		Map<String,Object> map = new HashMap<>();
		
		/**记住这个方法*/
		String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
		map.put("loginName",loginName);
		return map;
	}
}





