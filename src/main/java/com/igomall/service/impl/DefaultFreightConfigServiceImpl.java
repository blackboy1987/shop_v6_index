/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: uKRootqqMyFTxBPaYShfP6B1I+T5ksjO
 */
package com.igomall.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.dao.DefaultFreightConfigDao;
import com.igomall.entity.Area;
import com.igomall.entity.DefaultFreightConfig;
import com.igomall.entity.ShippingMethod;
import com.igomall.entity.Store;
import com.igomall.service.DefaultFreightConfigService;

/**
 * Service - 默认运费配置
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class DefaultFreightConfigServiceImpl extends BaseServiceImpl<DefaultFreightConfig, Long> implements DefaultFreightConfigService {

	@Resource
	private DefaultFreightConfigDao defaultFreightConfigDao;

	@Override
	@Transactional(readOnly = true)
	public boolean exists(ShippingMethod shippingMethod, Area area) {
		return defaultFreightConfigDao.exists(shippingMethod, area);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean unique(ShippingMethod shippingMethod, Area previousArea, Area currentArea) {
		return (previousArea != null && previousArea.equals(currentArea)) || !defaultFreightConfigDao.exists(shippingMethod, currentArea);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<DefaultFreightConfig> findPage(Store store, Pageable pageable) {
		return defaultFreightConfigDao.findPage(store, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public DefaultFreightConfig find(ShippingMethod shippingMethod, Store store) {
		return defaultFreightConfigDao.find(shippingMethod, store);
	}

	@Override
	public void update(DefaultFreightConfig defaultFreightConfig, Store store, ShippingMethod shippingMethod) {
		Assert.notNull(store, "[Assertion failed] - store is required; it must not be null");
		Assert.notNull(shippingMethod, "[Assertion failed] - shippingMethod is required; it must not be null");
		if (!defaultFreightConfig.isNew()) {
			super.update(defaultFreightConfig);
		} else {
			defaultFreightConfig.setStore(store);
			defaultFreightConfig.setShippingMethod(shippingMethod);
			super.save(defaultFreightConfig);
		}
	}

}