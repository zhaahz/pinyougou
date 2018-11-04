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
import tk.mybatis.mapper.entity.Example;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
/**
 * @Author ZhaJing
 * @Description //品牌管理类
 * @Date 9:23 2018/11/1
 * @version 1.0
 **/
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

	/**
	 * @Author ZhaJing
	 * @Description //删除品牌
	 * @Date 10:46 2018/11/1
	 * @Param [ids]
	 * @return
	 **/
	@Override
	public void deleteAll(Serializable[] ids) {

		/**这四句代码什么意思?
		 * 通用mapper的方式来删除*/
		//创建示范对象
		Example example = new Example(Brand.class);

		//创建条件对象
		Example.Criteria criteria = example.createCriteria();

		//添加in条件
		criteria.andIn("id", Arrays.asList(ids));

		//根据条件删除
		brandMapper.deleteByExample(example);

		//相当于给你产生一条条件删除语句
		//delete form tb_brand where id in(?,?);

	}

	@Override
	public Brand findOne(Serializable id) {
		return null;
	}


	/**
	 * @Author ZhaJing
	 * @Description //分页条件查询实现
	 * @Date 9:27 2018/11/1
	 * @Param [brand, page 当前页, rows 每页大小]
	 * @return 分页结果实体类 (总记录数,当前页数据)
	 **/
//	@Override
//	public PageResult findByPage(Brand brand, int page, int rows) {
//
//		try {
//			PageInfo<Brand> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(new ISelect() {
//				@Override
//				public void doSelect() {
//					brandMapper.findTiaoJian(brand);
//				}
//			});
//
//			return new PageResult(pageInfo.getTotal(),pageInfo.getList());
//		} catch (Exception e) {
//
//			throw new RuntimeException(e);
//		}
//	}

	//笔记: 直接在方法上抛出异常不妥,因为我们可以自定义service层的异常
	//然后try..catch 可以准确知道异常的种类

	@Override
	public PageInfo<Brand> findByPage(Brand brand, int page, int rows) {

		try {
			PageInfo<Brand> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(new ISelect() {
				@Override
				public void doSelect() {
					brandMapper.findTiaoJian(brand);
				}
			});

			return pageInfo;
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}
}
