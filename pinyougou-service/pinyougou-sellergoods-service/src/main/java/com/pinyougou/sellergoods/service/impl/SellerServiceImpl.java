package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.SellerMapper;
import com.pinyougou.pojo.Seller;
import com.pinyougou.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/5 10:48
 * @Description: // TODO 商家管理服务接口实现类
 * @Version: 1.0
 */
@Service(interfaceName = "com.pinyougou.service.SellerService")
@Transactional
public class SellerServiceImpl implements SellerService {
	@Autowired
	private SellerMapper sellerMapper;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 商家申请
	 * @Date 10:52 2018/11/5
	 * @Param [seller]
	 * @return
	 **/
	@Override
	public void save(Seller seller) {
		try {
			/**设置商家审核状态(未审核0)*/
			seller.setStatus("0");
			/**设置申请时间*/
			seller.setCreateTime(new Date());

			sellerMapper.insertSelective(seller);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据id查找商家
	 * @Date 20:12 2018/11/6
	 * @Param [id]
	 * @return
	 **/
	@Override
	public Seller findOne(Serializable id) {
		try{
			return sellerMapper.selectByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}


	/**
	 * @Author ZhaJing
	 * @Description //TODO 分页条件查询商家
	 * @Date 11:16 2018/11/5
	 * @Param [seller, page, rows]
	 * @return
	 **/
	@Override
	public PageInfo<Seller> findByPage(Seller seller, int page, int rows) {
		try {
			return PageHelper.startPage(page,rows).doSelectPageInfo(new ISelect() {
				@Override
				public void doSelect() {
					sellerMapper.findAll(seller);
				}
			});
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 修改商家的状态码
	 * @Date 12:40 2018/11/5
	 * @Param [sellerId, status]
	 * @return
	 **/
	@Override
	public void updateStatus(String sellerId, String status) {
		try {
			Seller seller = new Seller();
			seller.setSellerId(sellerId);
			seller.setStatus(status);
			sellerMapper.updateByPrimaryKey(seller);
		} catch (Exception e) {
			throw  new RuntimeException(e);
		}

		//通用mapper真的厉害
		//比如我要做个根据id修改状态码
		//一般做法是传入id值和修改的状态码值

		//而使用通用mapper时
		//只需用方法 update 传入参数为seller
		//并设置seller对象的id和status值
		//它就会自动映射成合适的sql语句

	}

	@Override
	public void update(Seller seller) {

	}

	@Override
	public void delete(Serializable id) {

	}

	@Override
	public void deleteAll(Serializable[] ids) {

	}



	@Override
	public List<Seller> findAll() {
		return null;
	}





}
