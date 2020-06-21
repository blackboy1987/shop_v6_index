/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: A0260xVofC2Hc9F47VnfY90jQSR0BIOu
 */
package com.igomall.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igomall.Filter;
import com.igomall.Order;
import com.igomall.dao.ProductTagDao;
import com.igomall.entity.ProductTag;
import com.igomall.service.ProductTagService;

/**
 * Service - 商品标签
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class ProductTagServiceImpl extends BaseServiceImpl<ProductTag, Long> implements ProductTagService {

	@Resource
	private ProductTagDao productTagDao;

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "productTag", condition = "#useCache")
	public List<ProductTag> findList(Integer count, List<Filter> filters, List<Order> orders, boolean useCache) {
		return productTagDao.findList(null, count, filters, orders);
	}

	@Override
	@Transactional
	@CacheEvict(value = "productTag", allEntries = true)
	public ProductTag save(ProductTag productTag) {
		return super.save(productTag);
	}

	@Override
	@Transactional
	@CacheEvict(value = "productTag", allEntries = true)
	public ProductTag update(ProductTag productTag) {
		return super.update(productTag);
	}

	@Override
	@Transactional
	@CacheEvict(value = "productTag", allEntries = true)
	public ProductTag update(ProductTag productTag, String... ignoreProperties) {
		return super.update(productTag, ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = "productTag", allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	@CacheEvict(value = "productTag", allEntries = true)
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	@CacheEvict(value = "productTag", allEntries = true)
	public void delete(ProductTag productTag) {
		super.delete(productTag);
	}

}