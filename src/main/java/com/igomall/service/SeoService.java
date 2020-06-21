/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: 6MY/wMb2bDIeeGT6QM2WpT9SkY45mB61
 */
package com.igomall.service;

import com.igomall.entity.Seo;

/**
 * Service - SEO设置
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface SeoService extends BaseService<Seo, Long> {

	/**
	 * 查找SEO设置
	 * 
	 * @param type
	 *            类型
	 * @return SEO设置
	 */
	Seo find(Seo.Type type);

	/**
	 * 查找SEO设置
	 * 
	 * @param type
	 *            类型
	 * @param useCache
	 *            是否使用缓存
	 * @return SEO设置
	 */
	Seo find(Seo.Type type, boolean useCache);

}