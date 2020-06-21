/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: TEqUs2xgK6Ff6RktxsUr2tHq01QZYm5s
 */
package com.igomall.service;

import java.util.List;

import com.igomall.entity.SitemapIndex;
import com.igomall.entity.SitemapUrl;

/**
 * Service - Sitemap索引
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface SitemapIndexService {

	/**
	 * 生成Sitemap索引
	 * 
	 * @param type
	 *            类型
	 * @param maxSitemapUrlSize
	 *            最大Sitemap URL数量
	 * @return Sitemap索引
	 */
	List<SitemapIndex> generate(SitemapUrl.Type type, int maxSitemapUrlSize);

}