package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.Seller;
import com.pinyougou.service.SellerService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/5 11:12
 * @Description: // TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/seller")
public class SellerController {
	@Reference(timeout = 10000)
	private SellerService sellerService;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 分页条件查询商家
	 * @Date 11:47 2018/11/5
	 * @Param [seller, page, rows]
	 * @return
	 **/
	@GetMapping("/findByPage")
	public Map<String,Object> findByPage(Seller seller,Integer page,Integer rows){
		try {
			if(seller != null && StringUtils.isNoneBlank(seller.getName())){
				seller.setName(new String(seller.getName().getBytes("ISO8859-1"),"UTF-8"));
			}
			if (seller != null && StringUtils.isNoneBlank(seller.getNickName())) {
				seller.setNickName(new String(seller.getNickName()
						.getBytes("ISO8859-1"), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<>();
		PageInfo<Seller> pageInfo = sellerService.findByPage(seller,page, rows);
		map.put("total",pageInfo.getTotal());
		map.put("rows",pageInfo.getList());
		return map;
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 修改状态码
	 * @Date 12:06 2018/11/5
	 * @Param [sellerId, status]
	 * @return
	 **/
	@GetMapping("/updateStatus")
	public boolean updateStatus( String sellerId,String status){
		try {
			sellerService.updateStatus(sellerId,status);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
