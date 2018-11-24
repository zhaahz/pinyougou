package com.pinyougou.user.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/23 23:49
 * @Description: // TODO 用户认证服务
 * @Version: 1.0
 */
public class UserDetailsServiceImpl implements UserDetailsService{

	/**
	 * @Author ZhaJing
	 * @Description //TODO 用户认证服务类
	 * @Date 23:50 2018/11/23
	 * @Param [s]
	 * @return
	 **/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new User(username,"",authorities);
	}
}
