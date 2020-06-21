
package com.igomall.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.igomall.dao.AftersalesSettingDao;
import com.igomall.entity.AftersalesSetting;
import com.igomall.entity.Store;
import com.igomall.service.AftersalesSettingService;

/**
 * Service - 售后设置
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class AftersalesSettingServiceImpl extends BaseServiceImpl<AftersalesSetting, Long> implements AftersalesSettingService {

	@Resource
	private AftersalesSettingDao aftersalesSettingDao;

	@Override
	@Transactional(readOnly = true)
	public AftersalesSetting findByStore(Store store) {
		Assert.notNull(store, "[Assertion failed] - store is required; it must not be null");

		return aftersalesSettingDao.find("store", store);
	}

}