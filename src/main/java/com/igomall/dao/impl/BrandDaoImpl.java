/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: C0phy6n6UoQw5/OBXc29+/BZouejiCMF
 */
package com.igomall.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.igomall.Filter;
import com.igomall.Order;
import com.igomall.dao.BrandDao;
import com.igomall.entity.Brand;
import com.igomall.entity.ProductCategory;

/**
 * Dao - 品牌
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Repository
public class BrandDaoImpl extends BaseDaoImpl<Brand, Long> implements BrandDao {

	@Override
	public List<Brand> findList(ProductCategory productCategory, Integer count, List<Filter> filters, List<Order> orders) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Brand> criteriaQuery = criteriaBuilder.createQuery(Brand.class);
		Root<Brand> root = criteriaQuery.from(Brand.class);
		criteriaQuery.select(root);
		if (productCategory != null) {
			criteriaQuery.where(criteriaBuilder.equal(root.join("productCategories"), productCategory));
		}
		return super.findList(criteriaQuery, null, count, filters, orders);
	}

}