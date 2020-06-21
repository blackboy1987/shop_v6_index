/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: hjvrTIfNyhk2SjS/DOHmS5iAF3zUegsW
 */
package com.igomall.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igomall.Filter;
import com.igomall.Order;
import com.igomall.dao.StoreRankDao;
import com.igomall.entity.StoreRank;
import com.igomall.service.StoreRankService;

/**
 * Service - 店铺等级
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class StoreRankServiceImpl extends BaseServiceImpl<StoreRank, Long> implements StoreRankService {

	@Resource
	private StoreRankDao storeRankDao;

	@Override
	@Transactional(readOnly = true)
	public boolean nameExists(String name) {
		return storeRankDao.exists("name", name);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean nameUnique(Long id, String name) {
		return storeRankDao.unique(id, "name", name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<StoreRank> findList(Boolean isAllowRegister, List<Filter> filters, List<Order> orders) {
		return storeRankDao.findList(isAllowRegister, filters, orders);
	}

}