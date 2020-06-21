/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: UJeTuB/k2kCETHtet722/t8Gj91hrFSG
 */
package com.igomall.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.dao.StockLogDao;
import com.igomall.entity.StockLog;
import com.igomall.entity.Store;
import com.igomall.service.StockLogService;

/**
 * Service - 库存记录
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class StockLogServiceImpl extends BaseServiceImpl<StockLog, Long> implements StockLogService {

	@Resource
	private StockLogDao stockLogDao;

	@Override
	@Transactional(readOnly = true)
	public Page<StockLog> findPage(Store store, Pageable pageable) {
		return stockLogDao.findPage(store, pageable);
	}

}