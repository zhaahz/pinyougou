package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.SpecificationMapper;
import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.pojo.Specification;
import com.pinyougou.pojo.SpecificationOption;
import com.pinyougou.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author ZhaJing
 * @Description //TODO 规格接口服务实现类
 * @Date 16:41 2018/11/7
 * @version 1.0
 **/
@Service(interfaceName = "com.pinyougou.service.SpecificationService")
@Transactional
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 分页条件查询
	 * @Date 16:40 2018/11/7
	 * @Param [specification, page, rows]
	 * @return
	 **/
	@Override
	public PageInfo<Specification> findByPage(Specification specification, int page, int rows) {
		try{
			// 开启分页
			return PageHelper.startPage(page, rows)
					.doSelectPageInfo(new ISelect() {
						@Override
						public void doSelect() {
							// 条件查询
							specificationMapper.findAll(specification);
						}
					});
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}



	/**
	 * @Author ZhaJing
	 * @Description //TODO 新增规格
	 * @Date 20:07 2018/11/7
	 * @Param [specification]
	 * @return 
	 **/
    @Override
    public void save(Specification specification) {
        try{
            // 往tb_specification规格表插入数据
			//插入一个规格名称
            specificationMapper.insertSelective(specification);

            // 往tb_specification_option规格选项表插入数据(多条数据)
            specificationOptionMapper.save(specification);

        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }



	/**
	 * @Author ZhaJing
	 * @Description //TODO 批量根据主键id删除规格
	 * @Date 18:39 2018/11/7
	 * @Param [ids]
	 * @return
	 **/
    @Override
    public void deleteAll(Serializable[] ids) {
		try {
			//1.创建示范对象
			Example example = new Example(Specification.class);
			//2.创建条件对象
			Example.Criteria criteria = example.createCriteria();
			//3.添加in条件
			criteria.andIn("id", Arrays.asList(ids));
			//4.根据条件删除
			specificationMapper.deleteByExample(example);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据规格id查询规格选项
	 * @Date 20:44 2018/11/7
	 * @Param [id]
	 * @return
	 **/
	@Override
	public List<SpecificationOption> findSpecOption(Long id) {
		try {
			SpecificationOption so = new SpecificationOption();
			so.setSpecId(id);
			return specificationOptionMapper.select(so);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 根据主键id修改规格
	 * @Date 20:51 2018/11/7
	 * @Param [specification]
	 * @return
	 **/
	@Override
	public void update(Specification specification) {
		//修改规格表数据
		specificationMapper.updateByPrimaryKey(specification);
		//修改规格选项表数据
		//1 删除带有spec_id的数据
		SpecificationOption so = new SpecificationOption();
		so.setSpecId(specification.getId());
		specificationOptionMapper.delete(so);

		//2 将修改后的数据插入到数据库中
		specificationOptionMapper.save(specification);

	}

    @Override
    public Specification findOne(Serializable id) {
        return null;
    }

    @Override
    public List<Specification> findAll() {
        return null;
    }



    
    /**
     * @Author ZhaJing
     * @Description //TODO  查询全部规格(id与specName)
     * @Date 10:10 2018/11/8
     * @Param []
     * @return 
     **/
    @Override
    public List<Map<String, Object>> findAllByIdAndName(){
        try{
            return specificationMapper.findAllByIdAndName();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }



	@Override
	public void delete(Serializable ids) {

	}
}
