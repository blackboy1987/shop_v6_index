/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: GjI+J8jOKmNMyMEOv/jHZjAvRbcrVSNH
 */
package com.igomall.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.igomall.dao.SnDao;
import com.igomall.entity.OrderRefunds;
import com.igomall.entity.Sn;
import com.igomall.service.OrderRefundsService;

/**
 * Service - 订单退款
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class OrderRefundsServiceImpl extends BaseServiceImpl<OrderRefunds, Long> implements OrderRefundsService {

	@Resource
	private SnDao snDao;

	@Override
	@Transactional
	public OrderRefunds save(OrderRefunds orderRefunds) {
		Assert.notNull(orderRefunds, "[Assertion failed] - orderRefunds is required; it must not be null");

		orderRefunds.setSn(snDao.generate(Sn.Type.ORDER_REFUNDS));

		return super.save(orderRefunds);
	}

}