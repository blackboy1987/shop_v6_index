
package com.igomall.dao.impl;

import com.igomall.dao.ProductCategoryDao;
import com.igomall.entity.ProductCategory;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.*;

/**
 * Dao - 商品分类
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Repository
public class ProductCategoryDaoImpl extends BaseDaoImpl<ProductCategory, Long> implements ProductCategoryDao {

	@Override
	public List<ProductCategory> findRoots(Integer count) {
		String jpql = "select productCategory from ProductCategory productCategory where productCategory.parent is null order by productCategory.order asc";
		TypedQuery<ProductCategory> query = entityManager.createQuery(jpql, ProductCategory.class);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	@Override
	public List<ProductCategory> findParents(ProductCategory productCategory, boolean recursive, Integer count) {
		if (productCategory == null || productCategory.getParent() == null) {
			return Collections.emptyList();
		}
		TypedQuery<ProductCategory> query;
		if (recursive) {
			String jpql = "select productCategory from ProductCategory productCategory where productCategory.id in (:ids) order by productCategory.grade asc";
			query = entityManager.createQuery(jpql, ProductCategory.class).setParameter("ids", Arrays.asList(productCategory.getParentIds()));
		} else {
			String jpql = "select productCategory from ProductCategory productCategory where productCategory = :productCategory";
			query = entityManager.createQuery(jpql, ProductCategory.class).setParameter("productCategory", productCategory.getParent());
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	@Override
	public List<ProductCategory> findChildren(ProductCategory productCategory, boolean recursive, Integer count) {
		TypedQuery<ProductCategory> query;
		if (recursive) {
			if (productCategory != null) {
				String jpql = "select productCategory from ProductCategory productCategory where productCategory.treePath like :treePath order by productCategory.grade asc, productCategory.order asc";
				query = entityManager.createQuery(jpql, ProductCategory.class).setParameter("treePath", "%" + ProductCategory.TREE_PATH_SEPARATOR + productCategory.getId() + ProductCategory.TREE_PATH_SEPARATOR + "%");
			} else {
				String jpql = "select productCategory from ProductCategory productCategory order by productCategory.grade asc, productCategory.order asc";
				query = entityManager.createQuery(jpql, ProductCategory.class);
			}
			if (count != null) {
				query.setMaxResults(count);
			}
			List<ProductCategory> result = query.getResultList();
			sort(result);
			return result;
		} else {
			String jpql = "select productCategory from ProductCategory productCategory where productCategory.parent = :parent order by productCategory.order asc";
			query = entityManager.createQuery(jpql, ProductCategory.class).setParameter("parent", productCategory);
			if (count != null) {
				query.setMaxResults(count);
			}
			return query.getResultList();
		}
	}

	/**
	 * 排序商品分类
	 * 
	 * @param productCategories
	 *            商品分类
	 */
	private void sort(List<ProductCategory> productCategories) {
		if (CollectionUtils.isEmpty(productCategories)) {
			return;
		}
		final Map<Long, Integer> orderMap = new HashMap<>();
		for (ProductCategory productCategory : productCategories) {
			orderMap.put(productCategory.getId(), productCategory.getOrder());
		}
		Collections.sort(productCategories, new Comparator<ProductCategory>() {
			@Override
			public int compare(ProductCategory productCategory1, ProductCategory productCategory2) {
				Long[] ids1 = (Long[]) ArrayUtils.add(productCategory1.getParentIds(), productCategory1.getId());
				Long[] ids2 = (Long[]) ArrayUtils.add(productCategory2.getParentIds(), productCategory2.getId());
				Iterator<Long> iterator1 = Arrays.asList(ids1).iterator();
				Iterator<Long> iterator2 = Arrays.asList(ids2).iterator();
				CompareToBuilder compareToBuilder = new CompareToBuilder();
				while (iterator1.hasNext() && iterator2.hasNext()) {
					Long id1 = iterator1.next();
					Long id2 = iterator2.next();
					Integer order1 = orderMap.get(id1);
					Integer order2 = orderMap.get(id2);
					compareToBuilder.append(order1, order2).append(id1, id2);
					if (!iterator1.hasNext() || !iterator2.hasNext()) {
						compareToBuilder.append(productCategory1.getGrade(), productCategory2.getGrade());
					}
				}
				return compareToBuilder.toComparison();
			}
		});
	}

}