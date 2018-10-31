package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.pojo.Brand;
import com.pinyougou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;

@Service(interfaceName = "com.pinyougou.service.BrandService")
@Transactional
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandMapper brandMapper;

	/**
	 * 分页查询全部品牌
	 * @return
	 */
	@Override
	public List<Brand> findAll() {

		//改造方法
		//return brandMapper.findAll();


		//PageHelper.startPage(1, 10);
		//List<Brand> list = brandMapper.findAll();
		//使用mapper通用的方法
		return brandMapper.selectAll();

		//PageInfo<Brand> pageInfo = new PageInfo<>(list);
		//return pageInfo.getList();

//		PageInfo<Brand> pageInfo = PageHelper.startPage(1, 5).doSelectPageInfo(new ISelect() {
//
//			@Override
//			public void doSelect() {
//
//				//对该查询语句进行分页
//				brandMapper.selectAll();
//			}
//		});
//
//		return pageInfo.getList();
	}

	@Override
	public void save(Brand brand) {

		//笔记 10/30 我看此处有两个insert方法,为什么不选择用另一个
		brandMapper.insertSelective(brand);
	}

	@Override
	public void update(Brand brand) {

		brandMapper.updateByPrimaryKeySelective(brand);


	}

	@Override
	public void delete(Serializable id) {

	}

	@Override
	public void deleteAll(Serializable[] ids) {

	}

	@Override
	public Brand findOne(Serializable id) {
		return null;
	}

	@Override
	public List<Brand> findByPage(Brand brand, int page, int rows) {
		return null;
	}
}
