/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: mRMQjsYdnKhrEP8PA8iJwqVQ3Ii1Phps
 */
package com.igomall.service;

import com.igomall.entity.AuditLog;

/**
 * Service - 审计日志
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface AuditLogService extends BaseService<AuditLog, Long> {

	/**
	 * 创建审计日志(异步)
	 * 
	 * @param auditLog
	 *            审计日志
	 */
	void create(AuditLog auditLog);

	/**
	 * 清空审计日志
	 */
	void clear();

}