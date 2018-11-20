package com.pinyougou.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import com.pinyougou.pojo.Goods;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * GoodsMapper 数据访问接口
 * @date 2018-10-29 21:26:28
 * @version 1.0
 */
public interface GoodsMapper extends Mapper<Goods>{


	List<Map<String,Object>> findAll(Goods record);

	/**
	 * @Author ZhaJing
	 * @Description //TODO 条件查询商品
	 * @Date 8:59 2018/11/9
	 * @Param [goods]
	 * @return
	 **/
	List<Goods> findByTiaoJian(Goods goods);

	/**
	 * @Author ZhaJing
	 * @Description //TODO 修改商品状态
	 * @Date 9:24 2018/11/9
	 * @Param [ids, status]
	 * @return 
	 **/
	void updateStatus(@Param("ids") Long[] ids, @Param("status") String status);

	/**
	 * @Author ZhaJing
	 * @Description //TODO 物理删除商品
	 * @Date 16:22 2018/11/17
	 * @Param [goods]
	 * @return 
	 **/
	void updateIsDelete(@Param("ids")Serializable[] ids,
						@Param("isDelete")String isDelete);


	//有多个参数,需要注意
	//要不用arg0,arg1表示
	//经过测试,arg0,arg1只能在注解中写
	//mybatis不支持在映射文件中写arg0,和arg1

	//就用注解@parm

	/**
	 * @Author ZhaJing
	 * @Description //TODO 商品的上架与下架
	 * @Date 16:58 2018/11/18
	 * @Param [isMarketable, ids]
	 * @return
	 **/
	void updateIsMarketable(@Param("isMarketable") String isMarketable,@Param("ids") Long[] ids);
}