/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: 1yJ/SiTB19MB8TBqqa6TTobbCiafzaHK
 */
package com.igomall.service;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.entity.DistributionCommission;
import com.igomall.entity.Distributor;

/**
 * Service - 分销佣金
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface DistributionCommissionService extends BaseService<DistributionCommission, Long> {

	/**
	 * 查找分销佣金记录分页
	 * 
	 * @param distributor
	 *            分销员
	 * @param pageable
	 *            分页信息
	 * @return 分销佣金记录分页
	 */
	Page<DistributionCommission> findPage(Distributor distributor, Pageable pageable);

}