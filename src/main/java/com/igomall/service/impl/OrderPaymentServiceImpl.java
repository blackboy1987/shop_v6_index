/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: xwCCuc+3oOBamPKaGoNxRvyPE6UQJttB
 */
package com.igomall.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.igomall.dao.OrderPaymentDao;
import com.igomall.dao.SnDao;
import com.igomall.entity.OrderPayment;
import com.igomall.entity.Sn;
import com.igomall.service.OrderPaymentService;

/**
 * Service - 订单支付
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class OrderPaymentServiceImpl extends BaseServiceImpl<OrderPayment, Long> implements OrderPaymentService {

	@Resource
	private OrderPaymentDao orderPaymentDao;
	@Resource
	private SnDao snDao;

	@Override
	@Transactional(readOnly = true)
	public OrderPayment findBySn(String sn) {
		return orderPaymentDao.find("sn", StringUtils.lowerCase(sn));
	}

	@Override
	@Transactional
	public OrderPayment save(OrderPayment orderPayment) {
		Assert.notNull(orderPayment, "[Assertion failed] - orderPayment is required; it must not be null");

		orderPayment.setSn(snDao.generate(Sn.Type.ORDER_PAYMENT));

		return super.save(orderPayment);
	}

}