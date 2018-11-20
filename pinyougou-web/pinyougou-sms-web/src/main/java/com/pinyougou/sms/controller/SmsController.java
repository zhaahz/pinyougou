package com.pinyougou.sms.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.service.SmsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/17 11:22
 * @Description: // TODO 短信控制器
 * @Version: 1.0
 */
@RestController
@RequestMapping("/sms")
public class SmsController {
	@Reference(timeout = 10000)
	private SmsService smsService;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 发送短信接口
	 * @Date 11:23 2018/11/17
	 * @Param [phone, signName, templateCode, templateParam]
	 * @return
	 **/
	@PostMapping("/sendSms")
	public Map<String,Object> sendSms(String phone, String signName,
					   String templateCode,
					   String templateParam){
		Map<String,Object> map = new HashMap<>();
		boolean success = smsService.sendSms(phone,signName,templateCode,templateParam);
		map.put("success",success);
		return map;
	}
}
