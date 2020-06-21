/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: vPzp+k9mHFn1oslOG/w8Svku8S8SPnkL
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
import com.igomall.dao.StoreAdImageDao;
import com.igomall.dao.StoreDao;
import com.igomall.entity.Store;
import com.igomall.entity.StoreAdImage;
import com.igomall.service.StoreAdImageService;

/**
 * Service - 店铺广告图片
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class StoreAdImageServiceImpl extends BaseServiceImpl<StoreAdImage, Long> implements StoreAdImageService {

	@Resource
	private StoreAdImageDao storeAdImageDao;
	@Resource
	private StoreDao storeDao;

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "storeAdImage", condition = "#useCache")
	public List<StoreAdImage> findList(Long storeId, Integer count, List<Filter> filters, List<Order> orders, boolean useCache) {
		Store store = storeDao.find(storeId);
		if (storeId != null && store == null) {
			return Collections.emptyList();
		}

		return storeAdImageDao.findList(store, count, filters, orders);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<StoreAdImage> findPage(Store store, Pageable pageable) {
		return storeAdImageDao.findPage(store, pageable);
	}

	@Override
	@CacheEvict(value = "storeAdImage", allEntries = true)
	public StoreAdImage save(StoreAdImage entity) {
		return super.save(entity);
	}

	@Override
	@CacheEvict(value = "storeAdImage", allEntries = true)
	public StoreAdImage update(StoreAdImage entity) {
		return super.update(entity);
	}

	@Override
	@CacheEvict(value = "storeAdImage", allEntries = true)
	public StoreAdImage update(StoreAdImage entity, String... ignoreProperties) {
		return super.update(entity, ignoreProperties);
	}

	@Override
	@CacheEvict(value = "storeAdImage", allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@CacheEvict(value = "storeAdImage", allEntries = true)
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@CacheEvict(value = "storeAdImage", allEntries = true)
	public void delete(StoreAdImage entity) {
		super.delete(entity);
	}

}