
package com.igomall.dao;

import com.igomall.entity.Seo;

/**
 * Dao - SEO设置
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface SeoDao extends BaseDao<Seo, Long> {

	/**
	 * 查找SEO设置
	 * 
	 * @param type
	 *            类型
	 * @return SEO设置
	 */
	Seo find(Seo.Type type);

}