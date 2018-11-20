package com.pinyougou.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.Content;
import com.pinyougou.mapper.ContentMapper;
import com.pinyougou.pojo.ContentCategory;
import com.pinyougou.service.ContentService;
import java.util.List;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.io.Serializable;
import java.util.Arrays;
/**
 * @Author ZhaJing
 * @Description //TODO 服务接口实现类
 * @Date 11:14 2018/11/9
 * @version 1.0
 **/
@Service(interfaceName = "com.pinyougou.service.ContentService" )
@Transactional
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentMapper contentMapper;
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 添加方法 
	 * @Date 11:14 2018/11/9
	 * @Param [content]
	 * @return 
	 **/
	@Override
	public void save(Content content){
		try {
			contentMapper.insertSelective(content);

			/**添加广告时要清除redis缓存*/
			redisTemplate.delete("content");
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 修改方法
	 * @Date 11:15 2018/11/9
	 * @Param [content]
	 * @return 
	 **/
	@Override
	public void update(Content content){
		try {
			contentMapper.updateByPrimaryKeySelective(content);

			/**修改广告时要清除redis缓存*/
			redisTemplate.delete("content");
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据主键id删除
	 * @Date 11:15 2018/11/9
	 * @Param [id]
	 * @return 
	 **/
	@Override
	public void delete(Serializable id){
		try {
			contentMapper.deleteByPrimaryKey(id);

			/**删除广告时要清除redis缓存*/
			redisTemplate.delete("content");
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 批量删除
	 * @Date 11:15 2018/11/9
	 * @Param [ids]
	 * @return 
	 **/
	@Override
	public void deleteAll(Serializable[] ids){
		try {
			// 创建示范对象
			Example example = new Example(Content.class);
			// 创建条件对象
			Example.Criteria criteria = example.createCriteria();
			// 创建In条件
			criteria.andIn("id", Arrays.asList(ids));
			// 根据示范对象删除
			contentMapper.deleteByExample(example);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据主键id查询
	 * @Date 11:15 2018/11/9
	 * @Param [id]
	 * @return 
	 **/
	@Override
	public Content findOne(Serializable id){
		try {
			return contentMapper.selectByPrimaryKey(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 查询全部 
	 * @Date 11:15 2018/11/9
	 * @Param []
	 * @return 
	 **/
	@Override
	public List<Content> findAll(){
		try {
			return contentMapper.selectAll();
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 多条件分页查询
	 * @Date 11:16 2018/11/9
	 * @Param [content, page, rows]
	 * @return 
	 **/
	@Override
	public PageResult findByPage(Content content, int page, int rows){
		try {
			PageInfo<Content> pageInfo = PageHelper.startPage(page, rows)
				.doSelectPageInfo(new ISelect() {
					@Override
					public void doSelect() {
						contentMapper.selectAll();
					}
				});
			return new PageResult(pageInfo.getTotal(),pageInfo.getList());
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据categoryId查询content广告内容 使用redis
	 * @Date 15:11 2018/11/9
	 * @Param [id]
	 * @return
	 **/
	@Override
	public List<Content> findContentByCategoryId(Long categoryId){
//			//注意还要封装其他条件的 比如 状态码为1 优先级(权重)
//	//所以使用通用mapper做吧
//	Example example = new Example(Content.class);
//	Example.Criteria criteria = example.createCriteria();
//	//添加等于条件 category_id = categoryId
//			criteria.andEqualTo("categoryId",categoryId);
//	//添加等于条件 status = 1
//			criteria.andEqualTo("status","1");
//	//排序(升序) order by sort_order asc
//			example.orderBy("sortOrder").asc();
//
//	//Content content = new Content();
//	//content.setCategoryId(categoryId);
//	return contentMapper.selectByExample(example);
        /** 定义广告数据 */
		List<Content> contentList = null;
		try {
        /** 从 Redis 中获取广告 */
			contentList = (List<Content>) redisTemplate.boundValueOps("content").get();
			if (contentList != null && contentList.size() > 0) {
				return contentList;
			}
		}catch (Exception ex){}
		try{
            // 创建示范对象
			Example example = new Example(Content.class);
            // 创建查询条件对象
			Example.Criteria criteria = example.createCriteria();
            // 添加等于条件 category_id = categoryId
			criteria.andEqualTo("categoryId", categoryId);
            // 添加等于条件 status = 1
			criteria.andEqualTo("status", "1");
            // 排序 ( 升序 ) order by sort_order asc
			example.orderBy("sortOrder").asc();
            /** 查询广告数据 */
			contentList = contentMapper.selectByExample(example);
			try {
             /** 存入 Redis 缓存 */
				redisTemplate.boundValueOps("content").set(contentList);
			}catch (Exception ex){}
			return contentList;
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

//	//注意还要封装其他条件的 比如 状态码为1 优先级(权重)
//	//所以使用通用mapper做吧
//	Example example = new Example(Content.class);
//	Example.Criteria criteria = example.createCriteria();
//	//添加等于条件 category_id = categoryId
//			criteria.andEqualTo("categoryId",categoryId);
//	//添加等于条件 status = 1
//			criteria.andEqualTo("status","1");
//	//排序(升序) order by sort_order asc
//			example.orderBy("sortOrder").asc();
//
//	//Content content = new Content();
//	//content.setCategoryId(categoryId);
//	contentList = contentMapper.selectByExample(example);

}