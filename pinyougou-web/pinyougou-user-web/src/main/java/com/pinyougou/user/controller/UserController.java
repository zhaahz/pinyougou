package com.pinyougou.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.User;
import com.pinyougou.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/user")
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
	public boolean save(@RequestBody User user,String smsCode){
		try {
			boolean ok = userService.checkSmsCode(user.getPhone(), smsCode);
			if(ok){
				userService.save(user);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 发送短信验证码
	 * @Date 21:30 2018/11/20
	 * @Param []
	 * @return
	 **/
	@GetMapping("/sendCode")
	public boolean sendCode(String phone){
		try {
			if(StringUtils.isNoneBlank(phone)){
				//发送验证码
				userService.sendCode(phone);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
