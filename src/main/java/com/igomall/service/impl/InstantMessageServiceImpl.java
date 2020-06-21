/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: 9XksLqCmIwFwpJ3t08dlXs+yKGnPzQzt
 */
package com.igomall.service.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igomall.Filter;
import com.igomall.Order;
import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.dao.InstantMessageDao;
import com.igomall.dao.StoreDao;
import com.igomall.entity.InstantMessage;
import com.igomall.entity.Store;
import com.igomall.service.InstantMessageService;

/**
 * Service - 即时通讯
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class InstantMessageServiceImpl extends BaseServiceImpl<InstantMessage, Long> implements InstantMessageService {

	@Resource
	private InstantMessageDao instantMessageDao;
	@Resource
	private StoreDao storeDao;

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "instantMessage", condition = "#useCache")
	public List<InstantMessage> findList(InstantMessage.Type type, Long storeId, Integer count, List<Filter> filters, List<Order> orders, boolean useCache) {
		Store store = storeDao.find(storeId);
		if (storeId != null && store == null) {
			return Collections.emptyList();
		}

		return instantMessageDao.findList(type, store, count, filters, orders);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<InstantMessage> findPage(Store store, Pageable pageable) {
		return instantMessageDao.findPage(store, pageable);
	}

	@Override
	@CacheEvict(value = "instantMessage", allEntries = true)
	public InstantMessage save(InstantMessage entity) {
		return super.save(entity);
	}

	@Override
	@CacheEvict(value = "instantMessage", allEntries = true)
	public InstantMessage update(InstantMessage entity) {
		return super.update(entity);
	}

	@Override
	@CacheEvict(value = "instantMessage", allEntries = true)
	public InstantMessage update(InstantMessage entity, String... ignoreProperties) {
		return super.update(entity, ignoreProperties);
	}

	@Override
	@CacheEvict(value = "instantMessage", allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@CacheEvict(value = "instantMessage", allEntries = true)
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@CacheEvict(value = "instantMessage", allEntries = true)
	public void delete(InstantMessage entity) {
		super.delete(entity);
	}

}