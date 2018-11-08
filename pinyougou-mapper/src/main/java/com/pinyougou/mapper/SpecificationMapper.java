package com.pinyougou.mapper;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import com.pinyougou.pojo.Specification;

import java.util.List;
import java.util.Map;

/**
 * SpecificationMapper 数据访问接口
 * @date 2018-10-29 21:26:28
 * @version 1.0
 */
public interface SpecificationMapper extends Mapper<Specification>{

	
	/**
	 * @Author ZhaJing
	 * @Description //TODO 多条件规格查询
	 * @Date 16:46 2018/11/7
	 * @Param [specification]
	 * @return 
	 **/
	List<Specification> findAll(Specification specification);

	
	/**
	 * @Author ZhaJing
	 * @Description //TODO  查询全部规格(id与specName)
	 * @Date 10:10 2018/11/8
	 * @Param []
	 * @return 
	 **/
	@Select("select id, spec_name as text from tb_specification order by id asc")
	List<Map<String,Object>> findAllByIdAndName();


}