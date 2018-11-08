package com.pinyougou.shop.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.pojo.Seller;
import com.pinyougou.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Date: Created in 2018/11/6 16:07
 * @Description: // TODO 用户验证类
 * @Version: 1.0
 */
public class UserDetialsServiceImpl implements UserDetailsService {
	/**注入商家服务接口代理对象*/
	private SellerService sellerService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/** 创建List集合封装角色 */
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		/** 添加角色 */
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));

		/** 根据登录名查询商家 */
		Seller seller = sellerService.findOne(username);

		/** 判断商家是否为空 并且 商家已审核 */
		if (seller != null && seller.getStatus().equals("1")){
			/** 返回用户信息对象 */
			return new User(username, seller.getPassword(), grantedAuthorities);
		}
		return null;
	}

	/** spring的setter注入 */
	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}
}
