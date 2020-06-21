/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: tll7pY9bZJMcUEgjHR3Yytt2dmI8k4q8
 */
package com.igomall.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.dao.DeliveryTemplateDao;
import com.igomall.entity.DeliveryCenter;
import com.igomall.entity.DeliveryTemplate;
import com.igomall.entity.Order;
import com.igomall.entity.Store;
import com.igomall.service.DeliveryTemplateService;

/**
 * Service - 快递单模板
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class DeliveryTemplateServiceImpl extends BaseServiceImpl<DeliveryTemplate, Long> implements DeliveryTemplateService {

	@Resource
	private DeliveryTemplateDao deliveryTemplateDao;

	@Override
	@Transactional(readOnly = true)
	public DeliveryTemplate findDefault(Store store) {
		return deliveryTemplateDao.findDefault(store);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DeliveryTemplate> findList(Store store) {
		return deliveryTemplateDao.findList(store);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<DeliveryTemplate> findPage(Store store, Pageable pageable) {
		return deliveryTemplateDao.findPage(store, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public String resolveContent(DeliveryTemplate deliveryTemplate, Store store, DeliveryCenter deliveryCenter, Order order) {
		Assert.notNull(deliveryTemplate, "[Assertion failed] - deliveryTemplate is required; it must not be null");

		List<String> tagNames = new ArrayList<>();
		List<String> values = new ArrayList<>();

		for (DeliveryTemplate.StoreAttribute storeAttribute : DeliveryTemplate.StoreAttribute.values()) {
			tagNames.add(storeAttribute.getTagName());
			values.add(storeAttribute.getValue(store));
		}
		for (DeliveryTemplate.DeliveryCenterAttribute deliveryCenterAttribute : DeliveryTemplate.DeliveryCenterAttribute.values()) {
			tagNames.add(deliveryCenterAttribute.getTagName());
			values.add(deliveryCenterAttribute.getValue(deliveryCenter));
		}
		for (DeliveryTemplate.OrderAttribute orderAttribute : DeliveryTemplate.OrderAttribute.values()) {
			tagNames.add(orderAttribute.getTagName());
			values.add(orderAttribute.getValue(order));
		}

		return StringUtils.replaceEachRepeatedly(deliveryTemplate.getContent(), tagNames.toArray(new String[tagNames.size()]), values.toArray(new String[values.size()]));
	}

	@Override
	@Transactional
	public DeliveryTemplate save(DeliveryTemplate deliveryTemplate) {
		Assert.notNull(deliveryTemplate, "[Assertion failed] - deliveryTemplate is required; it must not be null");

		if (BooleanUtils.isTrue(deliveryTemplate.getIsDefault())) {
			deliveryTemplateDao.clearDefault(deliveryTemplate.getStore());
		}
		return super.save(deliveryTemplate);
	}

	@Override
	@Transactional
	public DeliveryTemplate update(DeliveryTemplate deliveryTemplate) {
		Assert.notNull(deliveryTemplate, "[Assertion failed] - deliveryTemplate is required; it must not be null");

		DeliveryTemplate pDeliveryTemplate = super.update(deliveryTemplate);
		if (BooleanUtils.isTrue(pDeliveryTemplate.getIsDefault())) {
			deliveryTemplateDao.clearDefault(pDeliveryTemplate);
		}
		return pDeliveryTemplate;
	}

}