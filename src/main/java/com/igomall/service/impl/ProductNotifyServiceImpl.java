/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: yJR56xo2K5WMTfUzrP0Er9E0moOdLSdI
 */
package com.igomall.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.dao.ProductNotifyDao;
import com.igomall.entity.Member;
import com.igomall.entity.ProductNotify;
import com.igomall.entity.Sku;
import com.igomall.entity.Store;
import com.igomall.service.MailService;
import com.igomall.service.ProductNotifyService;

/**
 * Service - 到货通知
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class ProductNotifyServiceImpl extends BaseServiceImpl<ProductNotify, Long> implements ProductNotifyService {

	@Resource
	private ProductNotifyDao productNotifyDao;
	@Resource
	private MailService mailService;

	@Override
	@Transactional(readOnly = true)
	public boolean exists(Sku sku, String email) {
		return productNotifyDao.exists(sku, email);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ProductNotify> findPage(Store store, Member member, Boolean isMarketable, Boolean isOutOfStock, Boolean hasSent, Pageable pageable) {
		return productNotifyDao.findPage(store, member, isMarketable, isOutOfStock, hasSent, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Long count(Member member, Boolean isMarketable, Boolean isOutOfStock, Boolean hasSent) {
		return productNotifyDao.count(member, isMarketable, isOutOfStock, hasSent);
	}

	@Override
	public int send(List<ProductNotify> productNotifies) {
		if (CollectionUtils.isEmpty(productNotifies)) {
			return 0;
		}

		int count = 0;
		for (ProductNotify productNotify : productNotifies) {
			if (productNotify != null && StringUtils.isNotEmpty(productNotify.getEmail())) {
				mailService.sendProductNotifyMail(productNotify);
				productNotify.setHasSent(true);
				count++;
			}
		}
		return count;
	}

}