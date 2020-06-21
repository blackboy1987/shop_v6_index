
package com.igomall.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.LockModeType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.igomall.dao.SkuDao;
import com.igomall.dao.StockLogDao;
import com.igomall.entity.Product;
import com.igomall.entity.Product.Type;
import com.igomall.entity.Sku;
import com.igomall.entity.StockLog;
import com.igomall.entity.Store;
import com.igomall.service.SkuService;

/**
 * Service - SKU
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class SkuServiceImpl extends BaseServiceImpl<Sku, Long> implements SkuService {

	@Resource
	private SkuDao skuDao;
	@Resource
	private StockLogDao stockLogDao;

	@Override
	@Transactional(readOnly = true)
	public boolean snExists(String sn) {
		return skuDao.exists("sn", StringUtils.lowerCase(sn));
	}

	@Override
	@Transactional(readOnly = true)
	public Sku findBySn(String sn) {
		return skuDao.find("sn", StringUtils.lowerCase(sn));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sku> search(Store store, Product.Type type, String keyword, Set<Sku> excludes, Integer count) {
		return skuDao.search(store, type, keyword, excludes, count);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sku> findList(Store store, Type type, Set<Sku> matchs, Integer count) {
		return skuDao.findList(store, type, matchs, count);
	}

	@Override
	public void addStock(Sku sku, int amount, StockLog.Type type, String memo) {
		Assert.notNull(sku, "[Assertion failed] - sku is required; it must not be null");
		Assert.notNull(type, "[Assertion failed] - type is required; it must not be null");

		if (amount == 0) {
			return;
		}

		if (!LockModeType.PESSIMISTIC_WRITE.equals(skuDao.getLockMode(sku))) {
			skuDao.flush();
			skuDao.refresh(sku, LockModeType.PESSIMISTIC_WRITE);
		}

		Assert.notNull(sku.getStock(), "[Assertion failed] - sku stock is required; it must not be null");
		Assert.state(sku.getStock() + amount >= 0, "[Assertion failed] - sku stock must be equal or greater than 0");

		sku.setStock(sku.getStock() + amount);
		skuDao.flush();

		StockLog stockLog = new StockLog();
		stockLog.setType(type);
		stockLog.setInQuantity(amount > 0 ? amount : 0);
		stockLog.setOutQuantity(amount < 0 ? Math.abs(amount) : 0);
		stockLog.setStock(sku.getStock());
		stockLog.setMemo(memo);
		stockLog.setSku(sku);
		stockLogDao.persist(stockLog);
	}

	@Override
	public void addAllocatedStock(Sku sku, int amount) {
		Assert.notNull(sku, "[Assertion failed] - sku is required; it must not be null");

		if (amount == 0) {
			return;
		}

		if (!LockModeType.PESSIMISTIC_WRITE.equals(skuDao.getLockMode(sku))) {
			skuDao.flush();
			skuDao.refresh(sku, LockModeType.PESSIMISTIC_WRITE);
		}

		Assert.notNull(sku.getAllocatedStock(), "[Assertion failed] - sku allocatedStock is required; it must not be null");
		Assert.state(sku.getAllocatedStock() + amount >= 0, "[Assertion failed] - sku allocatedStock must be equal or greater than 0");

		sku.setAllocatedStock(sku.getAllocatedStock() + amount);
		skuDao.flush();
	}

	@Override
	@Transactional(readOnly = true)
	public void filter(List<Sku> skus) {
		CollectionUtils.filter(skus, new Predicate() {
			public boolean evaluate(Object object) {
				Sku sku = (Sku) object;
				return sku != null && sku.getStock() != null;
			}
		});
	}

}