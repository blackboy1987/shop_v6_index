
package com.igomall.dao;

import java.util.List;

import com.igomall.Filter;
import com.igomall.Order;
import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.entity.Store;
import com.igomall.entity.StoreAdImage;

/**
 * Dao - 店铺广告图片
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface StoreAdImageDao extends BaseDao<StoreAdImage, Long> {

	/**
	 * 查找店铺广告图片
	 * 
	 * @param store
	 *            店铺
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @return 店铺广告图片
	 */
	List<StoreAdImage> findList(Store store, Integer count, List<Filter> filters, List<Order> orders);

	/**
	 * 查找店铺广告图片分页
	 * 
	 * @param store
	 *            店铺
	 * @param pageable
	 *            分页信息
	 * @return 店铺广告图片分页
	 */
	Page<StoreAdImage> findPage(Store store, Pageable pageable);

}