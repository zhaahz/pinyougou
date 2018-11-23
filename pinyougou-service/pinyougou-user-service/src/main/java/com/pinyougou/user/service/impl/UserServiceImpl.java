package com.pinyougou.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.pinyougou.common.util.HttpClientUtils;
import com.pinyougou.mapper.UserMapper;
import com.pinyougou.pojo.User;
import com.pinyougou.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/17 12:34
 * @Description: // TODO 用户服务接口
 * @Version: 1.0
 */
@Service(interfaceName = "com.pinyougou.service.UserService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Value("${sms.url}")
	private String smsUrl;
	@Value("${sms.signName}")
	private String signName;
	@Value("${sms.templateCode}")
	private String templateCode;


	/**
	 * @Author ZhaJing
	 * @Description //TODO 注册用户
	 * @Date 12:35 2018/11/17
	 * @Param [user]
	 * @return
	 **/
	@Override
	public void save(User user) {
		try {
			//密码加密
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			//创建时间
			user.setCreated(new Date());
			//修改时间
			user.setUpdated(user.getCreated());

			userMapper.insertSelective(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}


	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 发送短信验证码
	 * @Date 21:33 2018/11/20
	 * @Param [phone]
	 * @return 
	 **/
	@Override
	public boolean sendCode(String phone) {

		try {
			//1.生成6位随机数
			String code = UUID.randomUUID().toString()
					.replaceAll("-", "")
					.replaceAll("[a-z|A-Z]", "")
					.substring(0, 6);
			System.out.println("验证码:"+code);

			//2.调用短信发送接口
			HttpClientUtils httpClientUtils = new HttpClientUtils(false);

			//3.创建Map集合封装请求参数
			Map<String,String> param = new HashMap<>();
			param.put("phone",phone);
			param.put("signName", signName);
			param.put("templateCode", templateCode);
			param.put("templateParam", "{\"number\":\"" + code + "\"}");

			//4.发送Post请求
			String content = httpClientUtils.sendPost(smsUrl, param);

			//5.吧json字符串转换为Map
			// 把json字符串转化成Map
			Map<String, Object> resMap = JSON.parseObject(content, Map.class);
			/** 存入Redis中(90秒) */
			redisTemplate.boundValueOps(phone).set(code, 90, TimeUnit.SECONDS);
			return (boolean)resMap.get("success");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		//下面几步什么意思?

	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 检查短信验证码是否正确
	 * @Date 10:56 2018/11/21
	 * @Param [phone, smsCode]
	 * @return
	 **/
	@Override
	public boolean checkSmsCode(String phone, String smsCode) {
		try {
			/** 获取Redis中存储的验证码 */
			String code = redisTemplate.boundValueOps(phone).get();
			return code != null && code.equals(smsCode);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public void update(User user) {

	}

	@Override
	public void delete(Serializable id) {

	}

	@Override
	public void deleteAll(Serializable[] ids) {

	}

	@Override
	public User findOne(Serializable id) {
		return null;
	}

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public List<User> findByPage(User user, int page, int rows) {
		return null;
	}


}
