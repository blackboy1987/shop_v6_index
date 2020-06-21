/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: kdKWokUHSAjh4DtHC08l0kf6SDSjVoGV
 */
package com.igomall.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.dao.DeliveryCenterDao;
import com.igomall.entity.DeliveryCenter;
import com.igomall.entity.Store;
import com.igomall.service.DeliveryCenterService;

/**
 * Service - 发货点
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class DeliveryCenterServiceImpl extends BaseServiceImpl<DeliveryCenter, Long> implements DeliveryCenterService {

	@Resource
	private DeliveryCenterDao deliveryCenterDao;

	@Override
	@Transactional(readOnly = true)
	public DeliveryCenter findDefault(Store store) {
		return deliveryCenterDao.findDefault(store);
	}

	@Override
	@Transactional
	public DeliveryCenter save(DeliveryCenter deliveryCenter) {
		Assert.notNull(deliveryCenter, "[Assertion failed] - deliveryCenter is required; it must not be null");

		if (BooleanUtils.isTrue(deliveryCenter.getIsDefault())) {
			deliveryCenterDao.clearDefault(deliveryCenter.getStore());
		}
		return super.save(deliveryCenter);
	}

	@Override
	@Transactional
	public DeliveryCenter update(DeliveryCenter deliveryCenter) {
		Assert.notNull(deliveryCenter, "[Assertion failed] - deliveryCenter is required; it must not be null");

		DeliveryCenter pDeliveryCenter = super.update(deliveryCenter);
		if (BooleanUtils.isTrue(pDeliveryCenter.getIsDefault())) {
			deliveryCenterDao.clearDefault(pDeliveryCenter);
		}
		return pDeliveryCenter;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<DeliveryCenter> findPage(Store store, Pageable pageable) {
		return deliveryCenterDao.findPage(store, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DeliveryCenter> findAll(Store store) {
		return deliveryCenterDao.findAll(store);
	}

}