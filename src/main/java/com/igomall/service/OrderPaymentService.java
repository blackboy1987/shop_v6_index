/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: u6EmX5Un2hA+jsRf/nxpnQwX+0lVHk5e
 */
package com.igomall.service;

import com.igomall.entity.OrderPayment;

/**
 * Service - 订单支付
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface OrderPaymentService extends BaseService<OrderPayment, Long> {

	/**
	 * 根据编号查找订单支付
	 * 
	 * @param sn
	 *            编号(忽略大小写)
	 * @return 订单支付，若不存在则返回null
	 */
	OrderPayment findBySn(String sn);

}