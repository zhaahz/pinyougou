package com.pinyougou.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.ContentCategory;
import com.pinyougou.mapper.ContentCategoryMapper;
import com.pinyougou.service.ContentCategoryService;
import java.util.List;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.io.Serializable;
import java.util.Arrays;
/**
 * @Author ZhaJing
 * @Description //TODO 服务接口实现类
 * @Date 11:16 2018/11/9
 * @version 1.0
 **/
@Service(interfaceName = "com.pinyougou.service.ContentCategoryService" )
@Transactional
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private ContentCategoryMapper contentCategoryMapper;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 添加广告分类
	 * @Date 11:17 2018/11/9
	 * @Param [contentCategory]
	 * @return 
	 **/
	@Override
	public void save(ContentCategory contentCategory){
		try {
			contentCategoryMapper.insertSelective(contentCategory);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 修改方法 
	 * @Date 11:17 2018/11/9
	 * @Param [contentCategory]
	 * @return 
	 **/
	@Override
	public void update(ContentCategory contentCategory){
		try {
			contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据主键id删除 
	 * @Date 11:17 2018/11/9
	 * @Param [id]
	 * @return 
	 **/
	@Override
	public void delete(Serializable id){
		try {
			contentCategoryMapper.deleteByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 批量删除
	 * @Date 11:17 2018/11/9
	 * @Param [ids]
	 * @return 
	 **/
	@Override
	public void deleteAll(Serializable[] ids){
		try {
			// 创建示范对象
			Example example = new Example(ContentCategory.class);
			// 创建条件对象
			Example.Criteria criteria = example.createCriteria();
			// 创建In条件
			criteria.andIn("id", Arrays.asList(ids));
			// 根据示范对象删除
			contentCategoryMapper.deleteByExample(example);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO  根据主键id查询
	 * @Date 11:17 2018/11/9
	 * @Param [id]
	 * @return 
	 **/
	@Override
	public ContentCategory findOne(Serializable id){
		try {
			return contentCategoryMapper.selectByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 查询全部 
	 * @Date 11:17 2018/11/9
	 * @Param []
	 * @return 
	 **/
	@Override
	public List<ContentCategory> findAll(){
		try {
			return contentCategoryMapper.selectAll();
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 多条件分页查询
	 * @Date 11:18 2018/11/9
	 * @Param [contentCategory, page, rows]
	 * @return 
	 **/
	@Override
	public PageResult findByPage(ContentCategory contentCategory, int page, int rows){
		try {
			PageInfo<ContentCategory> pageInfo = PageHelper.startPage(page, rows)
				.doSelectPageInfo(new ISelect() {
					@Override
					public void doSelect() {
						contentCategoryMapper.selectAll();
					}
				});
			return new PageResult(pageInfo.getTotal(),pageInfo.getList());
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

}