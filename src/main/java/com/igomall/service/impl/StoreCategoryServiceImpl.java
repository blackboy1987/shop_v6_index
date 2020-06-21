/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: gOYGOZcAP2Yy+n1GrTe3VJlRzHytdNxt
 */
package com.igomall.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igomall.dao.StoreCategoryDao;
import com.igomall.entity.StoreCategory;
import com.igomall.service.StoreCategoryService;

/**
 * Service - 店铺分类
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class StoreCategoryServiceImpl extends BaseServiceImpl<StoreCategory, Long> implements StoreCategoryService {

	@Resource
	private StoreCategoryDao storeCategoryDao;

	@Override
	@Transactional(readOnly = true)
	public boolean nameExists(String name) {
		return storeCategoryDao.exists("name", name, true);
	}

}