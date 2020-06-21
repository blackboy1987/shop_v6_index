
package com.igomall.service.impl;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.igomall.dao.AuditLogDao;
import com.igomall.entity.AuditLog;
import com.igomall.service.AuditLogService;

/**
 * Service - 审计日志
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class AuditLogServiceImpl extends BaseServiceImpl<AuditLog, Long> implements AuditLogService {

	@Resource
	private AuditLogDao auditLogDao;

	@Override
	@Async
	public void create(AuditLog auditLog) {
		auditLogDao.persist(auditLog);
	}

	@Override
	public void clear() {
		auditLogDao.removeAll();
	}

}