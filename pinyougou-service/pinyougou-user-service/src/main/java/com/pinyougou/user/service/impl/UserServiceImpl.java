package com.pinyougou.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.mapper.UserMapper;
import com.pinyougou.pojo.User;
import com.pinyougou.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
