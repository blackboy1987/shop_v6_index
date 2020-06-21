/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: /bvvZ8VH4xW/TdYQS2d41CCgntxtjHEl
 */
package com.igomall.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.igomall.Filter;
import com.igomall.Order;
import com.igomall.dao.StoreRankDao;
import com.igomall.entity.StoreRank;

/**
 * Dao - 店铺等级
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Repository
public class StoreRankDaoImpl extends BaseDaoImpl<StoreRank, Long> implements StoreRankDao {

	@Override
	public List<StoreRank> findList(Boolean isAllowRegister, List<Filter> filters, List<Order> orders) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StoreRank> criteriaQuery = criteriaBuilder.createQuery(StoreRank.class);
		Root<StoreRank> root = criteriaQuery.from(StoreRank.class);
		criteriaQuery.select(root);
		if (isAllowRegister != null) {
			criteriaQuery.where(criteriaBuilder.equal(root.get("isAllowRegister"), isAllowRegister));
		}
		return super.findList(criteriaQuery, null, null, filters, orders);
	}

}