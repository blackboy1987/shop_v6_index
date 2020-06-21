/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: z+ocyB02o9j0axKZ4rorfJr9rwzm1ORc
 */
package com.igomall.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.dao.MemberDepositLogDao;
import com.igomall.entity.Member;
import com.igomall.entity.MemberDepositLog;
import com.igomall.service.MemberDepositLogService;

/**
 * Service - 会员预存款记录
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class MemberDepositLogServiceImpl extends BaseServiceImpl<MemberDepositLog, Long> implements MemberDepositLogService {

	@Resource
	private MemberDepositLogDao memberDepositLogDao;

	@Override
	@Transactional(readOnly = true)
	public Page<MemberDepositLog> findPage(Member member, Pageable pageable) {
		return memberDepositLogDao.findPage(member, pageable);
	}

}