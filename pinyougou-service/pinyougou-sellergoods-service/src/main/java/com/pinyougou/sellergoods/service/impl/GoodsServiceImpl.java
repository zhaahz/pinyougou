package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.mapper.GoodsDescMapper;
import com.pinyougou.mapper.GoodsMapper;
import com.pinyougou.mapper.ItemCatMapper;
import com.pinyougou.mapper.ItemMapper;
import com.pinyougou.pojo.Goods;
import com.pinyougou.pojo.GoodsDesc;
import com.pinyougou.pojo.Item;
import com.pinyougou.pojo.ItemCat;
import com.pinyougou.service.GoodsService;
import org.apache.yetus.audience.InterfaceAudience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZhaJing
 * @Date: Created in 2018/11/6 08:41
 * @Description: // TODO 商品管理服务接口实现类
 * @Version: 1.0
 */
@Service(interfaceName = "com.pinyougou.service.GoodsService")
@Transactional
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsDescMapper goodsDescMapper;
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Autowired
	private ItemMapper itemMapper;

	/**
	 * @Author ZhaJing
	 * @Description //TODO 添加商品(tb_goods,tb_desc,tb_item)
	 * @Date 9:51 2018/11/6
	 * @Param [goods]
	 * @return
	 **/
	@Override
	public void save(Goods goods) {
		try {
			/**向tb_goods中插入数据*/
			goods.setAuditStatus("0");
			goodsMapper.insertSelective(goods);
			/**向tb_desc中插入数据*/
			goods.getGoodsDesc().setGoodsId(goods.getId());
			goodsDescMapper.insertSelective(goods.getGoodsDesc());
			/**向tb_item中插入数据*/
			//11/7/12:00
//			for (Item item : goods.getItems()) {
//				Item item1 = new Item();
//
//				itemMapper.insertSelective(item);
//			}

		}
			catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 查询全部商家待审核的商品
	 * @Date 8:56 2018/11/9
	 * @Param [goods, page, rows]
	 * @return
	 **/
	@Override
	public PageResult findByPage(Goods goods, int page, int rows) {
		try {
			PageInfo<Map<String,Object>> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(
					new ISelect() {
						@Override
						public void doSelect() {
							goodsMapper.findAll(goods);
						}
					}
			);
			//得到map集合,取出categoryId,得到categoryName
			List<Map<String, Object>> data = pageInfo.getList();
			for (Map<String, Object> map : data) {
				Long category1Id = (Long) map.get("category1Id");
				Long category2Id = (Long) map.get("category2Id");
				Long category3Id = (Long) map.get("category3Id");

				ItemCat itemCat1 = itemCatMapper.selectByPrimaryKey(category1Id);
				ItemCat itemCat2 = itemCatMapper.selectByPrimaryKey(category2Id);
				ItemCat itemCat3 = itemCatMapper.selectByPrimaryKey(category3Id);

				map.put("category1Name", itemCat1 != null ? itemCat1.getName() : "");
				map.put("category2Name", itemCat2 != null ? itemCat2.getName() : "");
				map.put("category3Name", itemCat3 != null ? itemCat3.getName() : "");

			}
			return new PageResult(pageInfo.getTotal(),pageInfo.getList());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 商品审核
	 * @Date 9:20 2018/11/9
	 * @Param [ids, status]
	 * @return
	 **/
	@Override
	public void updateStatus(Long[] ids, String status) {
		//update tb_goods set audit_status = 1 where id in (?,?,?)

		try {
			goodsMapper.updateStatus(ids,status);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 获取商品信息
	 * @Date 11:27 2018/11/15
	 * @Param [goodsId]
	 * @return 
	 **/
	@Override
	public Map<String, Object> getGoods(Long goodsId) {
		try {
			Map<String,Object> map = new HashMap<>();
			//获取tb_goods表中数据
			Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
			//获取tb_goods_desc表中数据
			GoodsDesc goodsDesc = goodsDescMapper.selectByPrimaryKey(goodsId);

			//面包屑[获取分类数据]
			if(goods!=null&&goods.getCategory3Id() != null) {
				String itemCat1 = itemCatMapper.selectByPrimaryKey(goods.getCategory1Id()).getName();
				String itemCat2 = itemCatMapper.selectByPrimaryKey(goods.getCategory2Id()).getName();
				String itemCat3 = itemCatMapper.selectByPrimaryKey(goods.getCategory3Id()).getName();
				map.put("itemCat1",itemCat1);
				map.put("itemCat2",itemCat2);
				map.put("itemCat3",itemCat3);
			}


			map.put("goodsDesc",goodsDesc);
			map.put("goods",goods);

			//查询SKU的数据
			Example example = new Example(Item.class);
			//查询条件对象
			Example.Criteria criteria = example.createCriteria();
			//状态码为1
			criteria.andEqualTo("status",1);
			//条件:SPU ID
			criteria.andEqualTo("goodsId",goodsId);
			// 排序(把默认的SKU排在最前面)
			example.orderBy("isDefault").desc();
			// 查询SKU表
			List<Item> itemList = itemMapper.selectByExample(example);

			// ${itemList} 把集合转化成json字符串
			map.put("itemList", JSON.toJSONString(itemList));
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 物理逻辑删除
	 * @Date 9:04 2018/11/17
	 * @Param [goods]
	 * @return
	 **/
	@Override
	public void updateIsDelete(Long[] ids,
							   String isDelete) {
		try {
			goodsMapper.updateIsDelete(ids,isDelete);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 商品的上架与下架
	 * @Date 16:57 2018/11/18
	 * @Param [isMarketable, ids]
	 * @return
	 **/
	@Override
	public void updateIsMarketable(String isMarketable, Long[] ids) {
		try {
			goodsMapper.updateIsMarketable(isMarketable,ids);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Author ZhaJing
	 * @Description //TODO 查询上架的SKU商品数据
	 * @Date 21:45 2018/11/19
	 * @Param [ids]
	 * @return
	 **/
	@Override
	public List<Item> findItemByGoodsId(Long[] ids) {
		try {
			Example example = new Example(Item.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andIn("goodsId", Arrays.asList(ids));
			return itemMapper.selectByExample(example);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public void update(Goods goods) {

	}

	@Override
	public void delete(Serializable id) {

	}

	@Override
	public void deleteAll(Serializable[] ids) {

	}

	@Override
	public Goods findOne(Serializable id) {
		return null;
	}

	@Override
	public List<Goods> findAll() {
		return null;
	}




}
