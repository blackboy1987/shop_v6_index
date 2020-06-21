/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: u4pdZYNXDuhPkVLD8tATw0GTRE5WsqPh
 */
package com.igomall.service;

import java.util.List;

import com.igomall.Filter;
import com.igomall.Order;
import com.igomall.entity.Navigation;

/**
 * Service - 导航
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface NavigationService extends BaseService<Navigation, Long> {

	/**
	 * 查找导航
	 * 
	 * @param navigationGroupId
	 *            导航组ID
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @param useCache
	 *            是否使用缓存
	 * @return 导航
	 */
	List<Navigation> findList(Long navigationGroupId, Integer count, List<Filter> filters, List<Order> orders, boolean useCache);

}