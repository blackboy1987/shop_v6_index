/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: 0v4Jk93rxDGjCB+4WsjxS2R4YUSfVL2C
 */
package com.igomall.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igomall.dao.SnDao;
import com.igomall.entity.Sn;
import com.igomall.service.SnService;

/**
 * Service - 序列号
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class SnServiceImpl implements SnService {

	@Resource
	private SnDao snDao;

	@Override
	@Transactional
	public String generate(Sn.Type type) {
		return snDao.generate(type);
	}

}