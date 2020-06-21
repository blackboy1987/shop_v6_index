/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: XmPqOJl0Q5dU/TXi9YikZ/IeiMB5ZPyf
 */
package com.igomall.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.igomall.Filter;
import com.igomall.Order;
import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.dao.InstantMessageDao;
import com.igomall.entity.InstantMessage;
import com.igomall.entity.InstantMessage.Type;
import com.igomall.entity.Store;

/**
 * Dao - 即时通讯
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Repository
public class InstantMessageDaoImpl extends BaseDaoImpl<InstantMessage, Long> implements InstantMessageDao {

	@Override
	public List<InstantMessage> findList(Type type, Store store, Integer count, List<Filter> filters, List<Order> orders) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<InstantMessage> criteriaQuery = criteriaBuilder.createQuery(InstantMessage.class);
		Root<InstantMessage> root = criteriaQuery.from(InstantMessage.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (type != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("type"), type));
		}
		if (store != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("store"), store));
		}
		criteriaQuery.where(restrictions);
		return super.findList(criteriaQuery, null, count, filters, orders);
	}

	@Override
	public Page<InstantMessage> findPage(Store store, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<InstantMessage> criteriaQuery = criteriaBuilder.createQuery(InstantMessage.class);
		Root<InstantMessage> root = criteriaQuery.from(InstantMessage.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (store != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("store"), store));
		}
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}

}