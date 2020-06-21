
package com.igomall.dao;

import java.util.List;

import com.igomall.Filter;
import com.igomall.Order;
import com.igomall.entity.Navigation;
import com.igomall.entity.NavigationGroup;

/**
 * Dao - 导航
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface NavigationDao extends BaseDao<Navigation, Long> {

	/**
	 * 查找导航
	 * 
	 * @param navigationGroup
	 *            导航组
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @return 导航
	 */
	List<Navigation> findList(NavigationGroup navigationGroup, Integer count, List<Filter> filters, List<Order> orders);

}