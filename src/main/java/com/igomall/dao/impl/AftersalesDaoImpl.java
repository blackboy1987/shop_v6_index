
package com.igomall.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.dao.AftersalesDao;
import com.igomall.entity.Aftersales;
import com.igomall.entity.Member;
import com.igomall.entity.OrderItem;
import com.igomall.entity.Store;

/**
 * Dao - 售后
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Repository
public class AftersalesDaoImpl extends BaseDaoImpl<Aftersales, Long> implements AftersalesDao {

	@Override
	public List<Aftersales> findList(List<OrderItem> orderItems) {
//		String jpql = "select aftersales from Aftersales aftersales where aftersales in(select aftersalesItem.aftersales from AftersalesItem aftersalesItem where aftersalesItem.orderItem in(:orderItems))";
//		TypedQuery<Aftersales> query = entityManager.createQuery(jpql, Aftersales.class);
//		return query.setParameter("orderItems", orderItems).getResultList();
		return null;
	}

	@Override
	public Page<Aftersales> findPage(Aftersales.Type type, Aftersales.Status status, Member member, Store store, Pageable pageable) {
//		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Aftersales> criteriaQuery = criteriaBuilder.createQuery(Aftersales.class);
//		Root<? extends Aftersales> root = criteriaQuery.from(type != null ? type.getClazz() : Aftersales.class);
//		criteriaQuery.select(root);
//		Predicate restrictions = criteriaBuilder.conjunction();
//		if (status != null) {
//			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("status"), status));
//		}
//		if (member != null) {
//			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), member));
//		}
//		if (store != null) {
//			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("store"), store));
//		}
//		criteriaQuery.where(restrictions);
//
//		return super.findPage(criteriaQuery, pageable);
		return null;
	}

}