/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: XdaYEYT+eS4xKMejL4wCNmS2wtYzZOVx
 */
package com.igomall.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.igomall.dao.SnDao;
import com.igomall.entity.OrderReturns;
import com.igomall.entity.Sn;
import com.igomall.service.OrderReturnsService;

/**
 * Service - 订单退货
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class OrderReturnsServiceImpl extends BaseServiceImpl<OrderReturns, Long> implements OrderReturnsService {

	@Resource
	private SnDao snDao;

	@Override
	@Transactional
	public OrderReturns save(OrderReturns orderReturns) {
		Assert.notNull(orderReturns, "[Assertion failed] - orderReturns is required; it must not be null");

		orderReturns.setSn(snDao.generate(Sn.Type.ORDER_RETURNS));

		return super.save(orderReturns);
	}

}