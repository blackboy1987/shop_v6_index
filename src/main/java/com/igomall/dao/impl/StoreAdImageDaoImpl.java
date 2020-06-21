
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
import com.igomall.dao.StoreAdImageDao;
import com.igomall.entity.Store;
import com.igomall.entity.StoreAdImage;

/**
 * Dao - 店铺广告图片
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Repository
public class StoreAdImageDaoImpl extends BaseDaoImpl<StoreAdImage, Long> implements StoreAdImageDao {

	@Override
	public List<StoreAdImage> findList(Store store, Integer count, List<Filter> filters, List<Order> orders) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StoreAdImage> criteriaQuery = criteriaBuilder.createQuery(StoreAdImage.class);
		Root<StoreAdImage> root = criteriaQuery.from(StoreAdImage.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (store != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("store"), store));
		}
		criteriaQuery.where(restrictions);
		return super.findList(criteriaQuery, null, count, filters, orders);
	}

	@Override
	public Page<StoreAdImage> findPage(Store store, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StoreAdImage> criteriaQuery = criteriaBuilder.createQuery(StoreAdImage.class);
		Root<StoreAdImage> root = criteriaQuery.from(StoreAdImage.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (store != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("store"), store));
		}
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}

}