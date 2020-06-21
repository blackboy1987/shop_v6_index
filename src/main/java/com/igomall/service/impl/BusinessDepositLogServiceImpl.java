/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: KnxPKBEidU0a0SGx01qVYYZw4ayWzsFS
 */
package com.igomall.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.dao.BusinessDepositLogDao;
import com.igomall.entity.Business;
import com.igomall.entity.BusinessDepositLog;
import com.igomall.service.BusinessDepositLogService;

/**
 * Service - 商家预存款记录
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class BusinessDepositLogServiceImpl extends BaseServiceImpl<BusinessDepositLog, Long> implements BusinessDepositLogService {

	@Resource
	private BusinessDepositLogDao businessDepositLogDao;

	@Override
	@Transactional(readOnly = true)
	public Page<BusinessDepositLog> findPage(Business business, Pageable pageable) {
		return businessDepositLogDao.findPage(business, pageable);
	}

}