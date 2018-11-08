package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.mapper.TypeTemplateMapper;
import com.pinyougou.pojo.Specification;
import com.pinyougou.pojo.SpecificationOption;
import com.pinyougou.pojo.TypeTemplate;
import com.pinyougou.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/6 12:40
 * @Description: // TODO 商品类型模板服务接口实现类
 * @Version: 1.0
 */
@Service(interfaceName = "com.pinyougou.service.TypeTemplateService")
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService {
	@Autowired
	private TypeTemplateMapper typeTemplateMapper;
	@Autowired
	private SpecificationOptionMapper specificationOptionMapper;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 分页条件查询商品类型模板
	 * @Date 21:20 2018/11/7
	 * @Param [typeTemplate, page, rows]
	 * @return
	 **/
	@Override
	public PageInfo<TypeTemplate> findByPage(TypeTemplate typeTemplate, int page, int rows){
		try {
			return PageHelper.startPage(page,rows).doSelectPageInfo(new ISelect() {
				@Override
				public void doSelect() {
					typeTemplateMapper.select(typeTemplate);
				}
			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//老师
//	@Override
//	public PageInfo<TypeTemplate> findByPage(TypeTemplate typeTemplate, int page, int rows) {
//		try{
//			// 开始分页
//			return PageHelper.startPage(page, rows)
//					.doSelectPageInfo(new ISelect() {
//						@Override
//						public void doSelect() {
//							// 多条件查询
//							// 创建示范对象
//							Example example = new Example(TypeTemplate.class);
//							// 创建条件对象
//							Example.Criteria criteria = example.createCriteria();
//							// 判断模板名称
//							if (typeTemplate != null && !StringUtils.isEmpty(typeTemplate.getName())) {
//								// name like ?
//								criteria.andLike("name", "%" + typeTemplate.getName() + "%");
//							}
//							// 条件查询
//							typeTemplateMapper.selectByExample(example);
//						}
//					});
//		}catch (Exception ex){
//			throw new RuntimeException(ex);
//		}
//	}




	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据商品类型模板主键查询
	 * @Date 12:54 2018/11/6
	 * @Param [id]
	 * @return 
	 **/
	@Override
	public TypeTemplate findOne(Serializable id) {
		try {
			return typeTemplateMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 添加模板数据
	 * @Date 10:31 2018/11/8
	 * @Param [typeTemplate]
	 * @return
	 **/
	@Override
	public void save(TypeTemplate typeTemplate){
		try {
			// 选择性添加，会判断对象的属性是否有值(有值就生成到insert语句中)
			typeTemplateMapper.insertSelective(typeTemplate);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 修改模板数据
	 * @Date 10:41 2018/11/8
	 * @Param [typeTemplate]
	 * @return
	 **/
	@Override
	public void update(TypeTemplate typeTemplate) {
		try{
			// 选择性修改，会判断对象的属性是否有值(有值就生成到update语句中)
			typeTemplateMapper.updateByPrimaryKeySelective(typeTemplate);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 删除规格
	 * @Date 10:49 2018/11/8
	 * @Param [ids]
	 * @return
	 **/
	@Override
	public void deleteAll(Serializable[] ids){
		try {
			Example example = new Example(TypeTemplate.class);

			Example.Criteria criteria = example.createCriteria();

			criteria.andIn("id",Arrays.asList(ids));

			typeTemplateMapper.deleteByExample(example);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}




	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据模板id查询所有的规格和规格选项
	 * @Date 9:45 2018/11/7
	 * @Param [id]
	 * @return 
	 **/
	@Override
	public List<Map> findSpecByTemplateId(Long id) {

		try {
			//根据id查询类型模板对象
			TypeTemplate typeTemplate = findOne(id);

			//得到spec_ids
			//[{"ids":27,"text":"网络"},{"ids":32,"text":"内存"}]
			String specIds = typeTemplate.getBrandIds();
			//怎么把数据库中的json字符串转换成想要的数据
			//fastJson中的方法
			//JSON.parseArray(); [{},{}] 获取的是list<map>
			//JSON.parseObject(); {}    获取的是map

			//把json字符串转化为List<map>集合
			List<Map> mapList = JSON.parseArray(specIds, Map.class);

			//遍历
			for (Map map : mapList) {
				//map {"ids":27,"text":"网络"}
	
				//获取id()
				Integer specId = (Integer) map.get("id");
	
				SpecificationOption so = new SpecificationOption();
				so.setSpecId(Long.valueOf(specId));
	
				//根据id查询得到选项 3g 4g
				List<SpecificationOption> soList = specificationOptionMapper.select(so);
	
				//map {"ids":27,"text":"网络","optains":[{},{}]}
				map.put("options",soList);
	
			}
			
			return mapList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 查找类型模板id和name(添加商品分类需要)
	 * @Date 14:48 2018/11/8
	 * @Param []
	 * @return 
	 **/
	@Override
	public List<TypeTemplate> findIdAndName() {
		return typeTemplateMapper.selectAll();
	}


	@Override
	public void delete(Serializable id) {

	}



	@Override
	public List<TypeTemplate> findAll() {
		return null;
	}


}
