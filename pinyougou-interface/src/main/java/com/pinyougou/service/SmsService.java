package com.pinyougou.service;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/17 11:14
 * @Description: // TODO 短信服务接口
 * @Version: 1.0
 */
public interface SmsService {

	/**
	 * @Author ZhaJing
	 * @Description //TODO 发送短信方法
	 * @Date 11:15 2018/11/17
	 * @Param [phone TODO 手机号码
	 * , signName TODO 签名
	 * , templateCode TODO 短信模板
	 * , templateParam TODO 模板参数(json格式)]
	 * @return true 发送成功 false 发送失败
	 **/
	public boolean sendSms(String phone,String signName,
						   String templateCode,
						   String templateParam);
}
