package com.pinyougou.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.User;
import com.pinyougou.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/17 12:31
 * @Description: // TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/save")
public class UserController {
	@Reference(timeout = 10000)
	private UserService userService;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 注册用户
	 * @Date 12:32 2018/11/17
	 * @Param [user]
	 * @return
	 **/
	@RequestMapping("/save")
	public boolean save(@RequestBody User user){
		try {
			userService.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
