/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: fsfqLcoZMI0CVx6JGMJXyaFnl4ezsY53
 */
package com.igomall.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.type.TypeReference;

import com.igomall.Setting;
import com.igomall.dao.OrderShippingDao;
import com.igomall.dao.SnDao;
import com.igomall.entity.OrderShipping;
import com.igomall.entity.Sn;
import com.igomall.service.OrderShippingService;
import com.igomall.util.JsonUtils;
import com.igomall.util.SystemUtils;
import com.igomall.util.WebUtils;

/**
 * Service - 订单发货
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class OrderShippingServiceImpl extends BaseServiceImpl<OrderShipping, Long> implements OrderShippingService {

	@Resource
	private OrderShippingDao orderShippingDao;
	@Resource
	private SnDao snDao;

	@Override
	@Transactional(readOnly = true)
	public OrderShipping findBySn(String sn) {
		return orderShippingDao.find("sn", StringUtils.lowerCase(sn));
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable("transitSteps")
	public List<Map<String, String>> getTransitSteps(OrderShipping orderShipping) {
		Assert.notNull(orderShipping, "[Assertion failed] - orderShipping is required; it must not be null");

		return getTransitSteps(orderShipping.getDeliveryCorpCode(), orderShipping.getTrackingNo());
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	@Cacheable("transitSteps")
	public List<Map<String, String>> getTransitSteps(String deliveryCorpCode, String trackingNo) {
		Setting setting = SystemUtils.getSetting();
		String kuaidi100Customer = setting.getKuaidi100Customer();
		String kuaidi100Key = setting.getKuaidi100Key();
		if (StringUtils.isEmpty(kuaidi100Customer) || StringUtils.isEmpty(kuaidi100Key) || StringUtils.isEmpty(deliveryCorpCode) || StringUtils.isEmpty(trackingNo)) {
			return Collections.emptyList();
		}

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("com", deliveryCorpCode);
		paramMap.put("num", trackingNo);
		String param = JsonUtils.toJson(paramMap);
		String sign = DigestUtils.md5Hex(param + kuaidi100Key + kuaidi100Customer).toUpperCase();

		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("customer", kuaidi100Customer);
		parameterMap.put("sign", sign);
		parameterMap.put("param", param);
		String content = WebUtils.post("http://poll.kuaidi100.com/poll/query.do", parameterMap);
		Map<String, Object> data = JsonUtils.toObject(content, new TypeReference<Map<String, Object>>() {
		});

		if (!StringUtils.equals(String.valueOf(data.get("message")), "ok")) {
			return Collections.emptyList();
		}
		return (List<Map<String, String>>) data.get("data");
	}

	@Override
	@Transactional
	public OrderShipping save(OrderShipping orderShipping) {
		Assert.notNull(orderShipping, "[Assertion failed] - orderShipping is required; it must not be null");

		orderShipping.setSn(snDao.generate(Sn.Type.ORDER_SHIPPING));

		return super.save(orderShipping);
	}

}