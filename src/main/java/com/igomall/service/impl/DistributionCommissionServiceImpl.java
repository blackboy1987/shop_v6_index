/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: s1ppJSM+L35I/VHH846AwVpUHlHcVO02
 */
package com.igomall.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.dao.DistributionCommissionDao;
import com.igomall.entity.DistributionCommission;
import com.igomall.entity.Distributor;
import com.igomall.service.DistributionCommissionService;

/**
 * Service - 分销佣金
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class DistributionCommissionServiceImpl extends BaseServiceImpl<DistributionCommission, Long> implements DistributionCommissionService {

	@Resource
	private DistributionCommissionDao distributionCommissionDao;

	@Override
	@Transactional(readOnly = true)
	public Page<DistributionCommission> findPage(Distributor distributor, Pageable pageable) {
		return distributionCommissionDao.findPage(distributor, pageable);
	}

}