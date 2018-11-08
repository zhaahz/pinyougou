package com.pinyougou.mapper;

import com.pinyougou.pojo.Specification;
import tk.mybatis.mapper.common.Mapper;

import com.pinyougou.pojo.SpecificationOption;

/**
 * SpecificationOptionMapper 数据访问接口
 * @date 2018-10-29 21:26:28
 * @version 1.0
 */
public interface SpecificationOptionMapper extends Mapper<SpecificationOption>{


	/**
	 * @Author ZhaJing
	 * @Description //TODO 批量插入数据
	 * @Date 21:09 2018/11/7
	 * @Param [specification]
	 * @return 
	 **/
	void save(Specification specification);

}