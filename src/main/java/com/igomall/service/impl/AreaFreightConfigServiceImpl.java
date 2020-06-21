
package com.igomall.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.dao.AreaFreightConfigDao;
import com.igomall.entity.Area;
import com.igomall.entity.AreaFreightConfig;
import com.igomall.entity.ShippingMethod;
import com.igomall.entity.Store;
import com.igomall.service.AreaFreightConfigService;

/**
 * Service - 地区运费配置
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class AreaFreightConfigServiceImpl extends BaseServiceImpl<AreaFreightConfig, Long> implements AreaFreightConfigService {

	@Resource
	private AreaFreightConfigDao areaFreightConfigDao;

	@Override
	@Transactional(readOnly = true)
	public boolean exists(ShippingMethod shippingMethod, Store store, Area area) {
		return areaFreightConfigDao.exists(shippingMethod, store, area);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean unique(Long id, ShippingMethod shippingMethod, Store store, Area area) {
		return areaFreightConfigDao.unique(id, shippingMethod, store, area);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<AreaFreightConfig> findPage(ShippingMethod shippingMethod, Store store, Pageable pageable) {
		return areaFreightConfigDao.findPage(shippingMethod, store, pageable);
	}

}