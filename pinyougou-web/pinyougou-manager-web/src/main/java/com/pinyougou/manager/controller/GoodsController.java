package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.Goods;
import com.pinyougou.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/9 08:51
 * @Description: // TODO 商品控制器
 * @Version: 1.0
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Reference(timeout = 10000)
	private GoodsService goodsService ;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 查询全部商家待审核的商品
	 * @Date 8:52 2018/11/9
	 * @Param [goods, page, rows]
	 * @return
	 **/
	@GetMapping("/findByPage")
	public PageResult findByPage(Goods goods,Integer page,Integer rows){
		//之所以在这里设置状态码的原因
		//是service层的分页条件查询想重复利用
		goods.setAuditStatus("0");
		//转码
		try {
			if(goods != null && StringUtils.isNoneBlank(goods.getGoodsName())){
				goods.setGoodsName(new String(goods.getGoodsName().getBytes("ISO8859-1"),"UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return goodsService.findByPage(goods,page,rows);
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 商品审核(修改商品状态)
	 * @Date 9:18 2018/11/9
	 * @Param [ids, status]
	 * @return
	 **/
	@GetMapping("/updateStatus")
	public boolean updateStatus(Long[] ids,String status){
		try {
			goodsService.updateStatus(ids,status);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 商品逻辑删除
	 * @Date 9:00 2018/11/17
	 * @Param [ids, isDelete]
	 * @return
	 **/
	@GetMapping("/updateIsDelete")
	public boolean updateIsDelete(Long[] ids,String isDelete){
		try {
			goodsService.updateIsDelete(ids,isDelete);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
