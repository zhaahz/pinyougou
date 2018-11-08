package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.GoodsDescMapper;
import com.pinyougou.mapper.GoodsMapper;
import com.pinyougou.mapper.ItemMapper;
import com.pinyougou.pojo.Goods;
import com.pinyougou.pojo.Item;
import com.pinyougou.service.GoodsService;
import org.apache.yetus.audience.InterfaceAudience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

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
			for (Item item : goods.getItems()) {
				Item item1 = new Item();

				itemMapper.insertSelective(item);
			}

		}
			catch (Exception e) {
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

	@Override
	public PageInfo<Goods> findByPage(Goods goods, int page, int rows) {
		return null;
	}
}
