/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: WbDGendveQoffnltvONW+cKO7JFs4Kq3
 */
package com.igomall.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.dao.DistributionCommissionDao;
import com.igomall.entity.DistributionCommission;
import com.igomall.entity.Distributor;

/**
 * Dao - 分销佣金
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Repository
public class DistributionCommissionDaoImpl extends BaseDaoImpl<DistributionCommission, Long> implements DistributionCommissionDao {

	@Override
	public Page<DistributionCommission> findPage(Distributor distributor, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DistributionCommission> criteriaQuery = criteriaBuilder.createQuery(DistributionCommission.class);
		Root<DistributionCommission> root = criteriaQuery.from(DistributionCommission.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (distributor != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("distributor"), distributor));
		}
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}

}