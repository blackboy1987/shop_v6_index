/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: huzAcmrL174KIRDAu5Hne0R608wIrK4C
 */
package com.igomall.service;

import java.util.List;

import com.igomall.Filter;
import com.igomall.Order;
import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.entity.Member;
import com.igomall.entity.Store;
import com.igomall.entity.StoreFavorite;

/**
 * Service - 店铺收藏
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface StoreFavoriteService extends BaseService<StoreFavorite, Long> {

	/**
	 * 判断店铺收藏是否存在
	 * 
	 * @param member
	 *            会员
	 * @param store
	 *            店铺
	 * @return 店铺收藏是否存在
	 */
	boolean exists(Member member, Store store);

	/**
	 * 查找店铺收藏
	 * 
	 * @param member
	 *            会员
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @return 店铺收藏
	 */
	List<StoreFavorite> findList(Member member, Integer count, List<Filter> filters, List<Order> orders);

	/**
	 * 查找店铺收藏
	 * 
	 * @param memberId
	 *            会员ID
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @param useCache
	 *            是否使用缓存
	 * @return 店铺收藏
	 */
	List<StoreFavorite> findList(Long memberId, Integer count, List<Filter> filters, List<Order> orders, boolean useCache);

	/**
	 * 查找店铺收藏分页
	 * 
	 * @param member
	 *            会员
	 * @param pageable
	 *            分页信息
	 * @return 店铺收藏分页
	 */
	Page<StoreFavorite> findPage(Member member, Pageable pageable);

	/**
	 * 查找店铺收藏数量
	 * 
	 * @param member
	 *            会员
	 * @return 店铺收藏数量
	 */
	Long count(Member member);

}